package com.testtask.movieapplication.presentation.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.testtask.movieapplication.R
import com.testtask.movieapplication.data.network.ApiClient
import com.testtask.movieapplication.data.network.AuthResponse
import com.testtask.movieapplication.data.network.LoginRequest
import com.testtask.movieapplication.presentation.ui.components.CustomButton
import com.testtask.movieapplication.presentation.ui.components.CustomText
import com.testtask.movieapplication.presentation.ui.components.CustomTextField
import com.testtask.movieapplication.presentation.ui.components.PasswordTextField
import com.testtask.movieapplication.presentation.ui.theme.GrayForFont
import com.testtask.movieapplication.presentation.ui.theme.MyYellow
import com.testtask.movieapplication.presentation.ui.theme.White50
import com.testtask.movieapplication.presentation.ui.theme.BlackForBackground
import com.testtask.movieapplication.presentation.ui.components.ClearFocusContainer
import com.testtask.movieapplication.presentation.viewmodels.MovieListViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun LoginScreen(navController: NavController, viewModel: MovieListViewModel = hiltViewModel()) {
    val emailState = rememberSaveable { mutableStateOf("") }
    val passwordState = rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    ClearFocusContainer(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackForBackground)
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))

            CustomText(
                text = stringResource(R.string.login_now),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(30.dp))

            Box (contentAlignment = Alignment.Center){
                Text(
                    text = stringResource(R.string.login_text),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = White50
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            CustomTextField(
                text = emailState.value,
                onTextChange = {emailState.value = it},
                placeholder =  { CustomText(
                    text = stringResource(R.string.email_address),
                    fontSize = 16.sp, fontWeight = FontWeight.Normal,
                    color = GrayForFont) },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            PasswordTextField(
                text = passwordState.value,
                onTextChange = {passwordState.value = it},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = stringResource(R.string.login_now),
                onClick = {
                    val login = emailState.value
                    val password = passwordState.value
                    if (login.isNotEmpty() && password.isNotEmpty()) {
                        loginUser(
                            context = context,
                            login = login,
                            password = password,
                            onSuccess = { navController.navigate("main") },
                            onError = { error -> /* Можно добавить дополнительную обработку */ }
                        )
                    } else {
                        Toast.makeText(
                            context,
                            "Заполните все поля",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            )

            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                CustomText(
                    text = stringResource(R.string.have_not_account),
                    fontSize = 16.sp,
                    color = GrayForFont,
                    fontWeight = FontWeight.Normal)

                TextButton(
                    onClick = { navController.navigate("register")} ,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.wrapContentWidth()
                ) {
                    CustomText(
                        text = stringResource(R.string.create_one),
                        fontSize = 16.sp,
                        color = MyYellow,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

private fun loginUser(
    context: Context,
    login: String,
    password: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val call = ApiClient.authApi.login(LoginRequest(login, password))
    call.enqueue(object : Callback<AuthResponse> {
        override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
            if (response.isSuccessful) {
                Toast.makeText(context, "Успешный вход!", Toast.LENGTH_SHORT).show()
                onSuccess()
            } else {
                Toast.makeText(context, "Ошибка входа", Toast.LENGTH_SHORT).show()
                onError("Ошибка входа")
            }
        }

        override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
            Toast.makeText(context, "Ошибка сети: ${t.message}", Toast.LENGTH_SHORT).show()
            onError(t.message ?: "Неизвестная ошибка сети")
        }
    })
}
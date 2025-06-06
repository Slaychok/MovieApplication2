package com.testtask.movieapplication.presentation.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.testtask.movieapplication.R
import com.testtask.movieapplication.data.network.ApiClient
import com.testtask.movieapplication.data.network.AuthResponse
import com.testtask.movieapplication.data.network.RegisterRequest
import com.testtask.movieapplication.presentation.ui.components.ClearFocusContainer
import com.testtask.movieapplication.presentation.ui.components.CustomButton
import com.testtask.movieapplication.presentation.ui.components.CustomText
import com.testtask.movieapplication.presentation.ui.components.CustomTextField
import com.testtask.movieapplication.presentation.ui.components.PasswordTextField
import com.testtask.movieapplication.presentation.ui.theme.BlackForBackground
import com.testtask.movieapplication.presentation.ui.theme.GrayForFont
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//@Composable
//fun RegisterScreen(navController: NavController) {
//    val userNameState = rememberSaveable { mutableStateOf("") }
//    val emailState = rememberSaveable { mutableStateOf("") }
//    val passwordState = rememberSaveable { mutableStateOf("") }
//
//    ClearFocusContainer(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(BlackForBackground)
//            .padding(16.dp),
//    ) {
//        Column(
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ){
//            Spacer(modifier = Modifier.weight(1f))
//
//            CustomText(
//                text = stringResource(R.string.create_account),
//                fontSize = 24.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.White
//            )
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            CustomTextField(
//                text = userNameState.value,
//                onTextChange = { userNameState.value = it },
//                placeholder = { CustomText(
//                    text = stringResource(R.string.username),
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Normal,
//                    color = GrayForFont) },
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            CustomTextField(
//                text = emailState.value,
//                onTextChange = { emailState.value = it },
//                placeholder = { CustomText(
//                    text = stringResource(R.string.email_address),
//                    fontSize = 16.sp,
//                    fontWeight = FontWeight.Normal,
//                    color = GrayForFont) },
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            PasswordTextField(
//                text = passwordState.value,
//                onTextChange = { passwordState.value = it },
//                modifier = Modifier.fillMaxWidth()
//            )
//
//            Spacer(modifier = Modifier.height(20.dp))
//
//            CustomButton(
//                modifier = Modifier.fillMaxWidth().height(56.dp),
//                text = stringResource(R.string.create_account),
//                onClick = {navController.navigate("main")}
//            )
//
//            Spacer(modifier = Modifier.weight(1f))
//        }
//    }
//}

@Composable
fun RegisterScreen(navController: NavController) {
    val userNameState = rememberSaveable { mutableStateOf("") }
    val emailState = rememberSaveable { mutableStateOf("") }
    val passwordState = rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    ClearFocusContainer(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackForBackground)
            .padding(16.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))

            CustomText(
                text = stringResource(R.string.create_account),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                text = userNameState.value,
                onTextChange = { userNameState.value = it },
                placeholder = {
                    CustomText(
                        text = stringResource(R.string.username),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = GrayForFont
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                text = emailState.value,
                onTextChange = { emailState.value = it },
                placeholder = {
                    CustomText(
                        text = stringResource(R.string.email_address),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = GrayForFont
                    )
                },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            PasswordTextField(
                text = passwordState.value,
                onTextChange = { passwordState.value = it },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = stringResource(R.string.create_account),
                onClick = {
                    val password = passwordState.value
                    val email = emailState.value

                    if (password.isNotEmpty() && email.isNotEmpty()) {
                        registerUser(
                            context = context,
                            password = password,
                            email = email,
                            onSuccess = { navController.navigate("login") },
                            onError = { error -> /* обработка ошибки */ }
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

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

private fun registerUser(
    context: Context,
    password: String,
    email: String,
    onSuccess: () -> Unit,
    onError: (String) -> Unit
) {
    val call = ApiClient.authApi.register(RegisterRequest(email, password))
    call.enqueue(object : Callback<AuthResponse> {
        override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
            if (response.isSuccessful) {
                Toast.makeText(context, "Регистрация успешна!", Toast.LENGTH_SHORT).show()
                onSuccess()
            } else {
                Toast.makeText(context, "Ошибка регистрации", Toast.LENGTH_SHORT).show()
                onError("Ошибка регистрации")
            }
        }

        override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
            Toast.makeText(context, "Ошибка сети: ${t.message}", Toast.LENGTH_SHORT).show()
            onError(t.message ?: "Неизвестная ошибка сети")
        }
    })
}

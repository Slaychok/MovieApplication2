package com.testtask.movieapplication.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
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
fun SettingsScreen(
    navController: NavController,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    ClearFocusContainer(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground)
            .padding(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.weight(1f))

            CustomText(
                text = "Настройки",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface //Color.White
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                text = "Изменить тему приложения",
                onClick = { onToggleTheme() }
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                text = "Выйти из аккаунта",
                onClick = { navController.navigate("login") }
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
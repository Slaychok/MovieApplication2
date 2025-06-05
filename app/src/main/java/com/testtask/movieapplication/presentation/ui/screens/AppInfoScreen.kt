package com.testtask.movieapplication.presentation.ui.screens

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
import com.testtask.movieapplication.presentation.ui.components.ClearFocusContainer
import com.testtask.movieapplication.presentation.ui.components.CustomButton
import com.testtask.movieapplication.presentation.ui.components.CustomText
import com.testtask.movieapplication.presentation.ui.components.CustomTextField
import com.testtask.movieapplication.presentation.ui.components.PasswordTextField
import com.testtask.movieapplication.presentation.ui.theme.BlackForBackground
import com.testtask.movieapplication.presentation.ui.theme.GrayForFont

@Composable
fun AppInfoScreen(navController: NavController) {
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
                text = "Данное приложение поможет определится с фильмом на вечер",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomText(
                text = "По всем вопросам tg:@Slaychok",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

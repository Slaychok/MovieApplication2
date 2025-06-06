package com.testtask.movieapplication.presentation.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.testtask.movieapplication.R
import com.testtask.movieapplication.presentation.navigation.bottomNavigation.BottomItem
import com.testtask.movieapplication.presentation.ui.components.CustomButton
import com.testtask.movieapplication.presentation.ui.components.CustomText
import com.testtask.movieapplication.presentation.ui.theme.GrayForFont
import com.testtask.movieapplication.presentation.ui.theme.MyYellow
import com.testtask.movieapplication.presentation.ui.theme.White50
import com.testtask.movieapplication.presentation.ui.theme.BlackForBackground
import com.testtask.movieapplication.presentation.ui.components.ClearFocusContainer

@Composable
fun ProfileScreen(navController: NavController) {
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

            Spacer(modifier = Modifier.height(30.dp))

            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = "Привет, пользователь! Добро пожаловать в приложение Киносправочник!",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = White50
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Spacer(modifier = Modifier.height(20.dp))

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "Избранное",
                onClick = {navController.navigate("featured")}
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                modifier = Modifier.fillMaxWidth().height(56.dp),
                text = "О приложении",
                onClick = {navController.navigate("info")}
            )

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
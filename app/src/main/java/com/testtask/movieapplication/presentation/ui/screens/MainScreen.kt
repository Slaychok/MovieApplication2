package com.testtask.movieapplication.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.testtask.movieapplication.presentation.navigation.bottomNavigation.BottomItem
import com.testtask.movieapplication.presentation.navigation.bottomNavigation.BottomNavigationBar
import com.testtask.movieapplication.presentation.ui.theme.BlackForBackground

@Composable
fun MainScreen(navController: NavController,
               isDarkTheme: Boolean,
               onToggleTheme: () -> Unit
){
    val mainNavController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onBackground),
        bottomBar = { BottomNavigationBar(mainNavController) }
    ) { paddingValues ->
        NavHost(
            navController = mainNavController,
            startDestination = BottomItem.ProfileScreen.route,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onBackground)
                .padding(paddingValues)
        ) {
            composable(BottomItem.ProfileScreen.route) {
                ProfileScreen(
                    navController = navController,
                )
            }
            composable(BottomItem.SearchScreen.route) {
                MovieListScreen(navController)
            }
            composable(BottomItem.SettingsScreen.route) {
                SettingsScreen(
                    navController = navController,
                    isDarkTheme = isDarkTheme,
                    onToggleTheme = onToggleTheme
                )
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview(){

}
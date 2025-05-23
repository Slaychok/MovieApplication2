package com.testtask.movieapplication.presentation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
fun MainScreen(navController: NavController){
    val mainNavController = rememberNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackForBackground),
        bottomBar = { BottomNavigationBar(mainNavController) }
    ) { paddingValues ->
        NavHost(
            navController = mainNavController,
            startDestination = BottomItem.HomeScreen.route,
            modifier = Modifier
                .fillMaxSize()
                .background(BlackForBackground)
                .padding(paddingValues)
        ) {
            composable(BottomItem.HomeScreen.route) {
                MovieListScreen(
                    navController = navController,
                )
            }
            composable(BottomItem.SearchScreen.route) {
                SearchScreen(navController)
            }
            composable(BottomItem.SettingsScreen.route) {
                SettingsScreen(navController)
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview(){

}
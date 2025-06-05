package com.testtask.movieapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.testtask.movieapplication.presentation.ui.screens.AppInfoScreen
import com.testtask.movieapplication.presentation.ui.screens.FeaturedScreen
import com.testtask.movieapplication.presentation.ui.screens.LoginScreen
import com.testtask.movieapplication.presentation.ui.screens.RegisterScreen
import com.testtask.movieapplication.presentation.ui.screens.MainScreen
import com.testtask.movieapplication.presentation.ui.screens.MovieDetailScreen
import com.testtask.movieapplication.presentation.ui.screens.ProfileScreen
import com.testtask.movieapplication.presentation.ui.screens.SettingsScreen

@Composable
fun AppNavigation(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("main") { MainScreen(navController, isDarkTheme, onToggleTheme) }
        composable("profile") { ProfileScreen(navController) }
        composable("info") { AppInfoScreen(navController) }
        composable("featured") { FeaturedScreen(navController) }
        composable("settings") {
            SettingsScreen(
                navController = navController,
                isDarkTheme = isDarkTheme,
                onToggleTheme = onToggleTheme
            ) }
        composable("movieDetail/{movieId}") { backStackEntry ->
            val movieId = backStackEntry.arguments?.getString("movieId")?.toIntOrNull()
            if (movieId != null) {
                MovieDetailScreen(movieId)
            }
        }
    }
}
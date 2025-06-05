package com.testtask.movieapplication.presentation.navigation.bottomNavigation

import androidx.annotation.DrawableRes
import com.testtask.movieapplication.R

sealed class BottomItem(@DrawableRes val iconId: Int, val route: String){

    data object ProfileScreen: BottomItem(R.drawable.home, "profile")
    data object SearchScreen: BottomItem(R.drawable.search, "search")
    data object SettingsScreen: BottomItem(R.drawable.settings, "settings")
}

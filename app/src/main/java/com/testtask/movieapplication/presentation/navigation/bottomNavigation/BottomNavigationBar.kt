package com.testtask.movieapplication.presentation.navigation.bottomNavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.testtask.movieapplication.presentation.ui.theme.BlackForBackground
import com.testtask.movieapplication.presentation.ui.theme.GrayForIcons

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomItem.HomeScreen,
        BottomItem.SearchScreen,
        BottomItem.SettingsScreen
    )

    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    Column {
        HorizontalDivider(
            color = GrayForIcons,
            thickness = 2.dp
        )

        NavigationBar(
            containerColor = BlackForBackground,
            contentColor = Color.White
        ) {
            items.forEach { item ->
                val isSelected = currentDestination?.route == item.route
                NavigationBarItem(
                    selected = isSelected,
                    onClick = { navController.navigate(item.route) },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.iconId),
                            contentDescription = item.route,
                            tint = if (isSelected) Color.White else GrayForIcons
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color.Transparent
                    )
                )
            }
        }
    }
}



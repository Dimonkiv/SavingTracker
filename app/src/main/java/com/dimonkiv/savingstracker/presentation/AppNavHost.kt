package com.dimonkiv.savingstracker.presentation

import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dimonkiv.savingstracker.presentation.account.add_account.AddAccountRoute
import com.dimonkiv.savingstracker.presentation.core.design_system.Dark
import com.dimonkiv.savingstracker.presentation.main.MainRoute
import com.dimonkiv.savingstracker.presentation.select_icon.SelectIconRoute

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Main.route
) {
    NavHost(
        modifier = modifier.background(Dark),
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Main.route) {
            MainRoute(navController)
        }
        composable(NavigationItem.Add.route) { entry ->
            AddAccountRoute(
                colorName = entry.savedStateHandle["color"],
                iconRes = entry.savedStateHandle["icon"],
                navController = navController
            )
        }
        composable(NavigationItem.SelectIcon.route) {
            SelectIconRoute(navController)
        }

    }
}
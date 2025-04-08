package com.dimonkiv.savingstracker.core

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dimonkiv.savingstracker.account.presentation.add_account.AddAccountRoute
import com.dimonkiv.savingstracker.core.design_system.Dark
import com.dimonkiv.savingstracker.main.MainRoute
import com.dimonkiv.savingstracker.select_icon.presentation.SelectIconRoute

@OptIn(ExperimentalMaterial3Api::class)
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
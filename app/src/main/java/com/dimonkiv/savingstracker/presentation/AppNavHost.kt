package com.dimonkiv.savingstracker.presentation

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dimonkiv.savingstracker.presentation.design_system.Dark
import com.dimonkiv.savingstracker.presentation.design_system.LightDark
import com.dimonkiv.savingstracker.presentation.main2.MainRoute

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
    }
}
package com.dimonkiv.savingstracker.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dimonkiv.savingstracker.account.presentation.accounts.AccountsRoute
import com.dimonkiv.savingstracker.core.NavigationItem
import com.dimonkiv.savingstracker.home.HomeScreen
import com.dimonkiv.savingstracker.profile.ProfileScreen
import com.dimonkiv.savingstracker.statistics.StatisticsScreen

@Composable
fun MainNavHost(
    modifier: Modifier,
    navController: NavHostController,
    mainNavController: NavHostController,
    startDestination: String = NavigationItem.Home.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
        composable(NavigationItem.Accounts.route) {
            AccountsRoute(mainNavController)
        }
        composable(NavigationItem.Statistics.route) {
            StatisticsScreen()
        }
        composable(NavigationItem.Profile.route) {
            ProfileScreen()
        }

    }

}
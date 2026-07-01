package com.dimonkiv.savingstracker.feature.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dimonkiv.savingstracker.core.navigation.NavigationItem

@Composable
fun MainNavHost(
    modifier: Modifier,
    navController: NavHostController,
    homeContent: @Composable () -> Unit,
    accountsContent: @Composable () -> Unit,
    statisticsContent: @Composable () -> Unit,
    profileContent: @Composable () -> Unit,
    startDestination: String = NavigationItem.Home.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Home.route) { homeContent() }
        composable(NavigationItem.Accounts.route) { accountsContent() }
        composable(NavigationItem.Statistics.route) { statisticsContent() }
        composable(NavigationItem.Profile.route) { profileContent() }
    }
}

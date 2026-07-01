package com.dimonkiv.savingstracker

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dimonkiv.savingstracker.core.navigation.NavigationItem
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme
import com.dimonkiv.savingstracker.feature.account.presentation.accounts.AccountsRoute
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.AddAccountRoute
import com.dimonkiv.savingstracker.feature.home.HomeScreen
import com.dimonkiv.savingstracker.feature.main.MainRoute
import com.dimonkiv.savingstracker.feature.profile.ProfileScreen
import com.dimonkiv.savingstracker.feature.select_icon.presentation.SelectIconRoute
import com.dimonkiv.savingstracker.feature.statistics.StatisticsScreen
import com.dimonkiv.savingstracker.feature.transaction.presentation.AddTransactionRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Main.route
) {
    NavHost(
        modifier = modifier.background(AppTheme.appColorScheme.background),
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Main.route) {
            MainRoute(
                mainController = navController,
                homeContent = { HomeScreen() },
                accountsContent = { AccountsRoute(navController) },
                statisticsContent = { StatisticsScreen() },
                profileContent = { ProfileScreen() }
            )
        }
        composable(NavigationItem.Add.route) {
            AddAccountRoute(navController = navController)
        }
        composable(NavigationItem.SelectIcon.route) {
            SelectIconRoute(navController)
        }
        composable(NavigationItem.AddTransaction.route) {
            AddTransactionRoute(navController)
        }
    }
}

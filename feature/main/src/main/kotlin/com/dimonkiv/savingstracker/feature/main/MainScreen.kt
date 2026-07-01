package com.dimonkiv.savingstracker.feature.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dimonkiv.savingstracker.core.navigation.Screen
import com.dimonkiv.savingstracker.feature.main.component.BottomNavigationBar
import com.dimonkiv.savingstracker.feature.main.model.BottomItemModel

@Composable
fun MainScreen(
    mainNavController: NavHostController,
    navController: NavHostController,
    items: List<BottomItemModel>,
    homeContent: @Composable () -> Unit,
    accountsContent: @Composable () -> Unit,
    statisticsContent: @Composable () -> Unit,
    profileContent: @Composable () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize()
            .navigationBarsPadding(),
        bottomBar = {
            BottomNavigationBar(
                items = items,
                currentDestination = currentDestination,
            ) { screen ->
                if (screen == Screen.ADD_TRANSACTION) {
                    mainNavController.navigate(screen.name)
                } else {
                    navController.navigate(screen.name) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    ) { innerPadding ->
        MainNavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            homeContent = homeContent,
            accountsContent = accountsContent,
            statisticsContent = statisticsContent,
            profileContent = profileContent
        )
    }
}

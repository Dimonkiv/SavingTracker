package com.dimonkiv.savingstracker.feature.main

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainRoute(
    mainController: NavHostController,
    homeContent: @Composable () -> Unit,
    accountsContent: @Composable () -> Unit,
    statisticsContent: @Composable () -> Unit,
    profileContent: @Composable () -> Unit,
    viewModel: MainViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val bottomNavController = rememberNavController()
    MainScreen(
        mainNavController = mainController,
        navController = bottomNavController,
        items = state,
        homeContent = homeContent,
        accountsContent = accountsContent,
        statisticsContent = statisticsContent,
        profileContent = profileContent
    )
}

package com.dimonkiv.savingstracker.feature.main

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.koin.compose.viewmodel.koinViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainRoute(
    mainController: NavHostController,
    viewModel: MainViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val bottomNavController = rememberNavController()
    MainScreen(mainController, bottomNavController, state)
}
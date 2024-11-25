package com.dimonkiv.savingstracker.main

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainRoute(
    mainController: NavHostController,
    viewModel: MainViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val bottomNavController = rememberNavController()
    MainScreen(mainController, bottomNavController, state)
}
package com.dimonkiv.savingstracker.presentation.main2

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.orbitmvi.orbit.compose.collectAsState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainRoute(
    navController: NavHostController,
    viewModel: MainViewModel2 = viewModel()
) {
    val state = viewModel.collectAsState(Lifecycle.State.CREATED)
    val bottomNavController = rememberNavController()
    MainScreen(bottomNavController, state.value)
}
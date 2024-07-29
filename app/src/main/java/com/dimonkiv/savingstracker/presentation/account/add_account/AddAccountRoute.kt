package com.dimonkiv.savingstracker.presentation.account.add_account

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.dimonkiv.savingstracker.presentation.NavigationItem
import com.dimonkiv.savingstracker.presentation.account.add_account.model.AddAccountModel

@Composable
fun AddAccountRoute(
    colorName: String?,
    iconRes: Int?,
    navController: NavHostController,
    viewModel: AddAccountViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    viewModel.setEvent(AddAccountContract.Event.OnDataReceived(colorName, iconRes))


    AddAccountScreen(
        model = state.value.model,
        onBackButtonClick = {
            openPreviousScreen(navController)
        },

        onSelectIconScreen = {
            openSelectIconScreen(navController)
        }
    )
}

private fun openPreviousScreen(navController: NavHostController) {
    navController.navigateUp()
}

private fun openSelectIconScreen(navController: NavHostController) {
    navController.navigate(NavigationItem.SelectIcon.route)
}
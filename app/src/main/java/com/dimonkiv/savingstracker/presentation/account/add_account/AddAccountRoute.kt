package com.dimonkiv.savingstracker.presentation.account.add_account

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.dimonkiv.savingstracker.presentation.NavigationItem

@ExperimentalMaterial3Api
@Composable
fun AddAccountRoute(
    colorName: String?,
    iconRes: Int?,
    navController: NavHostController,
    viewModel: AddAccountViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val bottomSheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    viewModel.setEvent(AddAccountContract.Event.OnDataReceived(colorName, iconRes))

    LaunchedEffect(key1 = Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is AddAccountContract.Effect.ShowSelectTypeScreen -> {
                    showBottomSheet = true
                }
            }
        }
    }

    AddAccountScreen(
        model = state.value.model,
        bottomSheetState,
        showBottomSheet,
        onBackButtonClick = {
            openPreviousScreen(navController)
        },

        onSelectIconScreen = {
            openSelectIconScreen(navController)
        },
        onTypeButtonClick = {
            viewModel.setEvent(AddAccountContract.Event.OnTypeClicked)
        },
        onDismissBottomSheet = {
            showBottomSheet = false
        },
        onTypeSelect = {
            showBottomSheet = false
            viewModel.setEvent(AddAccountContract.Event.OnTypeSelect(it))
        }
    )
}

private fun openPreviousScreen(navController: NavHostController) {
    navController.navigateUp()
}

private fun openSelectIconScreen(navController: NavHostController) {
    navController.navigate(NavigationItem.SelectIcon.route)
}
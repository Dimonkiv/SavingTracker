package com.dimonkiv.savingstracker.account.presentation.addaccount

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.dimonkiv.savingstracker.core.NavigationItem
import com.dimonkiv.savingstracker.core.compose.AppToolbar
import com.dimonkiv.savingstracker.core.compose.BaseScreen
import com.dimonkiv.savingstracker.shared.ConsumeUiEffects

@ExperimentalMaterial3Api
@Composable
fun AddAccountRoute(
    colorName: String?,
    iconRes: Int?,
    navController: NavHostController,
    viewModel: AddAccountViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val bottomSheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.OnDataReceived(colorName, iconRes))
    }

    ConsumeUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is Effect.ShowSelectTypeSheet -> {
                showBottomSheet = true
            }

            is Effect.HideSelectTypeSheet -> {
                showBottomSheet = false
            }

            is Effect.OpenPreviousScreen -> {
                openPreviousScreen(navController)
            }

            is Effect.OpenSelectIconScreen -> {
                openSelectIconScreen(navController)
            }
        }
    }

    BaseScreen(
        toolbar = {
            AppToolbar(
                title = "Add account",
                onNavigationIconClicked = {
                    viewModel.setEvent(Event.OnBackButtonClicked)
                }
            )
        }
    ) {
        AddAccountScreen(
            model = state.value,
            bottomSheetState,
            showBottomSheet,
            onEventChanged = {
                viewModel.setEvent(it)
            }
        )
    }
}

private fun openPreviousScreen(navController: NavHostController) {
    navController.navigateUp()
}

private fun openSelectIconScreen(navController: NavHostController) {
    navController.navigate(NavigationItem.SelectIcon.route)
}
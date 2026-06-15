package com.dimonkiv.savingstracker.feature.account.presentation.addaccount

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
import com.dimonkiv.savingstracker.core.navigation.NavigationItem
import com.dimonkiv.savingstracker.core.ui.AppToolbar
import com.dimonkiv.savingstracker.core.ui.BaseScreen
import com.dimonkiv.savingstracker.core.mvi.ConsumeUiEffects
import org.koin.compose.viewmodel.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun AddAccountRoute(
    colorName: String?,
    iconRes: Int?,
    navController: NavHostController,
    viewModel: AddAccountViewModel = koinViewModel()
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
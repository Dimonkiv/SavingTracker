package com.dimonkiv.savingstracker.select_icon.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.dimonkiv.savingstracker.core.compose.AppToolbar
import com.dimonkiv.savingstracker.core.compose.BaseScreen
import com.dimonkiv.savingstracker.shared.ConsumeUiEffects

@Composable
fun SelectIconRoute(
    navController: NavHostController,
    viewModel: SelectIconViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ConsumeUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is SelectIconContract.Effect.OpenPreviousScreenWithArgs -> {
                val savedState = navController.previousBackStackEntry?.savedStateHandle
                savedState?.set("color", effect.color)
                savedState?.set("icon", effect.iconRes)

                navController.navigateUp()
            }
        }
    }

    BaseScreen(
        toolbar = {
            AppToolbar(
                title = "Select icon",
                onNavigationIconClicked = {
                    navController.navigateUp()
                }
            )
        },
        content = {
            SelectIconScreen(
                state = state,
                onEventChanged = { event ->
                    viewModel.setEvent(event)
                }
            )
        }
    )
}
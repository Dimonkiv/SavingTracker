package com.dimonkiv.savingstracker.feature.select_icon.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.dimonkiv.savingstracker.core.designsystem.R
import com.dimonkiv.savingstracker.designsystem.AppToolbar
import com.dimonkiv.savingstracker.designsystem.BaseScreen
import com.dimonkiv.savingstracker.core.mvi.ConsumeUiEffects
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SelectIconRoute(
    navController: NavHostController,
    viewModel: SelectIconViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ConsumeUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is SelectIconEffect.OpenPreviousScreenWithArgs -> {
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
                title = stringResource(R.string.select_icon),
                onNavigationIconClicked = {
                    navController.navigateUp()
                }
            )
        },
        content = {
            SelectIconScreen(
                state = state,
                onIntent = { event ->
                    viewModel.handleIntent(event)
                }
            )
        }
    )
}
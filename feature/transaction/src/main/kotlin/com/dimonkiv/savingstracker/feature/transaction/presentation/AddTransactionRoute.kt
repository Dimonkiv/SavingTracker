package com.dimonkiv.savingstracker.feature.transaction.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.dimonkiv.savingstracker.core.designsystem.R
import com.dimonkiv.savingstracker.core.mvi.ConsumeUiEffects
import com.dimonkiv.savingstracker.designsystem.AppToolbar
import com.dimonkiv.savingstracker.designsystem.BaseScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddTransactionRoute(
    navController: NavHostController,
    viewModel: AddTransactionViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    ConsumeUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is AddTransactionEffect.NavigateBack -> navController.navigateUp()
            is AddTransactionEffect.ShowError -> snackbarHostState.showSnackbar(effect.message)
        }
    }

    BaseScreen(
        snackbarHostState = snackbarHostState,
        toolbar = {
            AppToolbar(
                title = stringResource(R.string.new_transaction),
                onNavigationIconClicked = {
                    viewModel.handleIntent(AddTransactionIntent.OnBackClicked)
                }
            )
        },
        content = {
            AddTransactionScreen(
                state = state,
                onIntent = { viewModel.handleIntent(it) }
            )
        }
    )
}

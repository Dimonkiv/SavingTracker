package com.dimonkiv.savingstracker.feature.transaction.presentation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dimonkiv.savingstracker.core.designsystem.R
import com.dimonkiv.savingstracker.core.mvi.ConsumeUiEffects
import com.dimonkiv.savingstracker.core.navigation.Navigator
import com.dimonkiv.savingstracker.core.navigation.routes.SelectTransactionAccount
import com.dimonkiv.savingstracker.core.navigation.routes.SelectTransactionDate
import com.dimonkiv.savingstracker.designsystem.AppToolbar
import com.dimonkiv.savingstracker.designsystem.BaseScreen
import com.dimonkiv.savingstracker.feature.transaction.di.TransactionAccountResultBus
import com.dimonkiv.savingstracker.feature.transaction.di.TransactionDateResultBus
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddTransactionRoute(
    navigator: Navigator,
    viewModel: AddTransactionViewModel = koinViewModel(),
    accountResult: TransactionAccountResultBus = koinInject(),
    dateResult: TransactionDateResultBus = koinInject()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(accountResult.value) {
        accountResult.value?.let {
            viewModel.handleIntent(AddTransactionIntent.OnAccountSelected(it))
            accountResult.consume()
        }
    }

    LaunchedEffect(dateResult.value) {
        dateResult.value?.let {
            viewModel.handleIntent(AddTransactionIntent.OnDateChanged(it))
            dateResult.consume()
        }
    }

    ConsumeUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is AddTransactionEffect.NavigateBack -> navigator.goBack()
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
                onSelectDateClicked = { navigator.navigate(SelectTransactionDate(state.timestamp)) },
                onSelectAccountClicked = { navigator.navigate(SelectTransactionAccount) },
                onIntent = { viewModel.handleIntent(it) }
            )
        }
    )
}

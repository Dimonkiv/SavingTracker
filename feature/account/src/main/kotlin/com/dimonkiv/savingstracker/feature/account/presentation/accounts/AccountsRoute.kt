package com.dimonkiv.savingstracker.feature.account.presentation.accounts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dimonkiv.savingstracker.core.mvi.ConsumeUiEffects
import com.dimonkiv.savingstracker.core.navigation.Navigator
import com.dimonkiv.savingstracker.core.navigation.routes.AddAccount
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AccountsRoute(
    navigator: Navigator,
    viewModel: AccountsViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ConsumeUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is AccountsEffect.NavigateToAddAccount -> navigator.navigate(AddAccount)
        }
    }

    AccountsScreen(
        state = state,
        onIntent = { viewModel.handleIntent(it) }
    )
}
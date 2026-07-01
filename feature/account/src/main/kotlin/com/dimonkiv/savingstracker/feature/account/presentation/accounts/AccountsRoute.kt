package com.dimonkiv.savingstracker.feature.account.presentation.accounts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.dimonkiv.savingstracker.core.mvi.ConsumeUiEffects
import com.dimonkiv.savingstracker.core.navigation.NavigationItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AccountsRoute(
    navController: NavHostController,
    viewModel: AccountsViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ConsumeUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is AccountsEffect.NavigateToAddAccount -> navController.navigate(NavigationItem.Add.route)
        }
    }

    AccountsScreen(
        state = state,
        onIntent = { viewModel.handleIntent(it) }
    )
}
package com.dimonkiv.savingstracker.feature.account.presentation.accounts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.dimonkiv.savingstracker.core.navigation.NavigationItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AccountsRoute(
    navController: NavHostController,
    viewModel: AccountsViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AccountsScreen(
        state = state,
        onAddClick = { navController.navigate(NavigationItem.Add.route) },
        onErrorDismiss = { viewModel.handleIntent(AccountsIntent.OnErrorDialogClick) }
    )
}
package com.dimonkiv.savingstracker.presentation.account.accounts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dimonkiv.savingstracker.presentation.NavigationItem

@Composable
fun AccountsRoute(
    navController: NavHostController,
    viewModel: AccountsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val navBackStackEntry = remember { navController.getBackStackEntry(NavigationItem.Main.route) }
    LaunchedEffect(navBackStackEntry) {
        viewModel.setEvent(AccountContract.Event.LoadAccounts)
    }

    AccountsScreen(
        state = state,
        onOkErrorClick = {
            viewModel.setEvent(AccountContract.Event.OnErrorDialogClick)
        },
        onAddClick = {
            navController.navigate(NavigationItem.Add.route)
        }
    )
}
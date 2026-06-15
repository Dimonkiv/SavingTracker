package com.dimonkiv.savingstracker.feature.account.presentation.accounts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
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
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(navController) {
        navController.currentBackStackEntryFlow.collect { backStackEntry ->
            val isResumed = backStackEntry.destination.route == NavigationItem.Main.route
            val resumedState = lifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED

            if (isResumed && resumedState) {
                viewModel.setEvent(Event.LoadAccounts)
            }
        }
    }

        AccountsScreen(
            state = state,
            onOkErrorClick = {
                viewModel.setEvent(Event.OnErrorDialogClick)
            },
            onAddClick = {
                navController.navigate(NavigationItem.Add.route)
            }
        )

}
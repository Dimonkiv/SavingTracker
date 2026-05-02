package com.dimonkiv.savingstracker.transaction.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.core.compose.AppToolbar
import com.dimonkiv.savingstracker.core.compose.BaseScreen

@Composable
fun AddTransactionRoute(
    navController: NavHostController,
    viewModel: AddTransactionViewModel = hiltViewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.setEvent(Event.LoadData)
    }

    BaseScreen(
        toolbar = {
            AppToolbar(
                title = stringResource(R.string.new_transaction),
                onNavigationIconClicked = {
                    navController.navigateUp()
                }
            )
        },
        content = {
            AddTransactionScreen(
                state = state.value,
                onEventChanged = { event ->
                    viewModel.setEvent(event)
                }
            )
        }
    )
}
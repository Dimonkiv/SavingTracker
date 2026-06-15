package com.dimonkiv.savingstracker.feature.transaction.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.core.ui.AppToolbar
import com.dimonkiv.savingstracker.core.ui.BaseScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddTransactionRoute(
    navController: NavHostController,
    viewModel: AddTransactionViewModel = koinViewModel()
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
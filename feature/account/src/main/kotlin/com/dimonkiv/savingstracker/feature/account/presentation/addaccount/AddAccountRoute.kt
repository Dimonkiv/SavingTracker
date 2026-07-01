package com.dimonkiv.savingstracker.feature.account.presentation.addaccount

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.ui.res.stringResource
import com.dimonkiv.savingstracker.core.designsystem.R
import com.dimonkiv.savingstracker.core.navigation.Navigator
import com.dimonkiv.savingstracker.core.navigation.routes.IconColorResultBus
import com.dimonkiv.savingstracker.core.navigation.routes.SelectAccountType
import com.dimonkiv.savingstracker.core.navigation.routes.SelectIcon
import com.dimonkiv.savingstracker.designsystem.AppToolbar
import com.dimonkiv.savingstracker.designsystem.BaseScreen
import com.dimonkiv.savingstracker.core.mvi.ConsumeUiEffects
import com.dimonkiv.savingstracker.feature.account.di.AccountTypeResultBus
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun AddAccountRoute(
    navigator: Navigator,
    viewModel: AddAccountViewModel = koinViewModel(),
    iconColorResult: IconColorResultBus = koinInject(),
    accountTypeResult: AccountTypeResultBus = koinInject()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(iconColorResult.value) {
        iconColorResult.value?.let {
            viewModel.handleIntent(AddAccountIntent.OnIconAndColorResult(it.colorName, it.iconRes))
            iconColorResult.consume()
        }
    }

    LaunchedEffect(accountTypeResult.value) {
        accountTypeResult.value?.let {
            viewModel.handleIntent(AddAccountIntent.OnTypeSelected(it))
            accountTypeResult.consume()
        }
    }

    ConsumeUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is AddAccountEffect.ShowSelectTypeSheet -> {
                navigator.navigate(SelectAccountType)
            }

            is AddAccountEffect.OpenPreviousScreen -> {
                navigator.goBack()
            }

            is AddAccountEffect.OpenSelectIconScreen -> {
                navigator.navigate(SelectIcon)
            }

            is AddAccountEffect.ShowError -> {
                snackbarHostState.showSnackbar(effect.message)
            }
        }
    }

    BaseScreen(
        snackbarHostState = snackbarHostState,
        toolbar = {
            AppToolbar(
                title = stringResource(R.string.add_account),
                onNavigationIconClicked = {
                    viewModel.handleIntent(AddAccountIntent.OnBackButtonClicked)
                }
            )
        }
    ) {
        AddAccountScreen(
            model = state,
            onIntent = {
                viewModel.handleIntent(it)
            }
        )
    }
}
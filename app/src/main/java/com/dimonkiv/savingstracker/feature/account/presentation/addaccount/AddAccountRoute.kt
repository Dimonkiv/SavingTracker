package com.dimonkiv.savingstracker.feature.account.presentation.addaccount

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.compose.ui.res.stringResource
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.core.navigation.NavigationItem
import com.dimonkiv.savingstracker.designsystem.AppToolbar
import com.dimonkiv.savingstracker.designsystem.BaseScreen
import com.dimonkiv.savingstracker.core.mvi.ConsumeUiEffects
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import org.koin.compose.viewmodel.koinViewModel

@ExperimentalMaterial3Api
@Composable
fun AddAccountRoute(
    navController: NavHostController,
    viewModel: AddAccountViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val bottomSheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        val handle = navController.currentBackStackEntry?.savedStateHandle
        combine(
            handle?.getStateFlow("color", null as String?) ?: flowOf(null),
            handle?.getStateFlow("icon", null as Int?) ?: flowOf(null)
        ) { colorName, iconRes -> colorName to iconRes }
            .collect { (colorName, iconRes) ->
                viewModel.handleIntent(Intent.OnIconAndColorResult(colorName, iconRes))
            }
    }

    ConsumeUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is Effect.ShowSelectTypeSheet -> {
                showBottomSheet = true
            }

            is Effect.HideSelectTypeSheet -> {
                showBottomSheet = false
            }

            is Effect.OpenPreviousScreen -> {
                openPreviousScreen(navController)
            }

            is Effect.OpenSelectIconScreen -> {
                openSelectIconScreen(navController)
            }

            is Effect.ShowError -> {
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
                    viewModel.handleIntent(Intent.OnBackButtonClicked)
                }
            )
        }
    ) {
        AddAccountScreen(
            model = state,
            bottomSheetState,
            showBottomSheet,
            onEventChanged = {
                viewModel.handleIntent(it)
            }
        )
    }
}

private fun openPreviousScreen(navController: NavHostController) {
    navController.navigateUp()
}

private fun openSelectIconScreen(navController: NavHostController) {
    navController.navigate(NavigationItem.SelectIcon.route)
}
package com.dimonkiv.savingstracker.feature.select_icon.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dimonkiv.savingstracker.core.designsystem.R
import com.dimonkiv.savingstracker.core.navigation.Navigator
import com.dimonkiv.savingstracker.core.navigation.routes.IconColorResult
import com.dimonkiv.savingstracker.core.navigation.routes.IconColorResultBus
import com.dimonkiv.savingstracker.designsystem.AppToolbar
import com.dimonkiv.savingstracker.designsystem.BaseScreen
import com.dimonkiv.savingstracker.core.mvi.ConsumeUiEffects
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SelectIconRoute(
    navigator: Navigator,
    iconColorResult: IconColorResultBus = koinInject(),
    viewModel: SelectIconViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ConsumeUiEffects(viewModel.effect) { effect ->
        when (effect) {
            is SelectIconEffect.OpenPreviousScreenWithArgs -> {
                iconColorResult.send(IconColorResult(iconRes = effect.iconRes, colorName = effect.color))
                navigator.goBack()
            }
        }
    }

    BaseScreen(
        toolbar = {
            AppToolbar(
                title = stringResource(R.string.select_icon),
                onNavigationIconClicked = {
                    navigator.goBack()
                }
            )
        },
        content = {
            SelectIconScreen(
                state = state,
                onIntent = { event ->
                    viewModel.handleIntent(event)
                }
            )
        }
    )
}
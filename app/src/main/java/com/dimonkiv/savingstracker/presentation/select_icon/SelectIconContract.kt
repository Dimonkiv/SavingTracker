package com.dimonkiv.savingstracker.presentation.select_icon

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.presentation.core.model.UiEffect
import com.dimonkiv.savingstracker.presentation.core.model.UiEvent
import com.dimonkiv.savingstracker.presentation.core.model.UiState
import com.dimonkiv.savingstracker.presentation.select_icon.model.SelectedIconModel

class SelectIconContract {

    sealed class Event: UiEvent {
        data object LoadData: Event()
        data object OnSelectButtonClick: Event()
        data class OnColorSelected(val color: Color): Event()
        data class OnIconSelected(val icon: Int): Event()
    }

    data class State(
        val state: SelectIconState
    ): UiState

    sealed class Effect: UiEffect {
        data class OpenPreviousScreenWithArgs(val iconRes: Int, val color: String): Effect()
    }

    sealed class SelectIconState {
        data object Idle: SelectIconState()
        data object Loading: SelectIconState()
        data class Success(val state: SelectedIconModel): SelectIconState()
    }
}
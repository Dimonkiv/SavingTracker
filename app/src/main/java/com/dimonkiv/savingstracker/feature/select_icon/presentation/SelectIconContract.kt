package com.dimonkiv.savingstracker.feature.select_icon.presentation

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.core.mvi.model.UiAction
import com.dimonkiv.savingstracker.core.mvi.model.UiEffect
import com.dimonkiv.savingstracker.core.mvi.model.UiIntent
import com.dimonkiv.savingstracker.feature.select_icon.presentation.model.ColorModel
import com.dimonkiv.savingstracker.feature.select_icon.presentation.model.IconModel

class SelectIconContract {

    sealed class Intent: UiIntent {
        data object OnSelectButtonClick: Intent()
        data class OnColorSelected(val color: Color): Intent()
        data class OnIconSelected(val icon: Int): Intent()
    }

    sealed class Effect: UiEffect {
        data class OpenPreviousScreenWithArgs(val iconRes: Int, val color: String): Effect()
    }

    sealed interface SelectIconAction: UiAction {
        data class SetColor(val color: Color): SelectIconAction
        data class SetIcon(val iconRes: Int): SelectIconAction
    }
}
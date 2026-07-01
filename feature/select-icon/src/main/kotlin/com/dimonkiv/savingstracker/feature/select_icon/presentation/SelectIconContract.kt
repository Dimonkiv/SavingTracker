package com.dimonkiv.savingstracker.feature.select_icon.presentation

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.core.mvi.model.UiAction
import com.dimonkiv.savingstracker.core.mvi.model.UiEffect
import com.dimonkiv.savingstracker.core.mvi.model.UiIntent

sealed class SelectIconIntent : UiIntent {
    data object OnSelectButtonClick : SelectIconIntent()
    data class OnColorSelected(val color: Color) : SelectIconIntent()
    data class OnIconSelected(val icon: Int) : SelectIconIntent()
}

sealed class SelectIconEffect : UiEffect {
    data class OpenPreviousScreenWithArgs(val iconRes: Int, val color: String) : SelectIconEffect()
}

sealed interface SelectIconAction : UiAction {
    data class SetColor(val color: Color) : SelectIconAction
    data class SetIcon(val iconRes: Int) : SelectIconAction
}

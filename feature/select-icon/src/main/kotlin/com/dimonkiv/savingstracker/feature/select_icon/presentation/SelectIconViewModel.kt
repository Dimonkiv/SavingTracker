package com.dimonkiv.savingstracker.feature.select_icon.presentation

import com.dimonkiv.savingstracker.core.mvi.BaseViewModel
import com.dimonkiv.savingstracker.designsystem.theme.ColorMap
import com.dimonkiv.savingstracker.designsystem.theme.IconMap
import com.dimonkiv.savingstracker.feature.select_icon.presentation.model.SelectedIconModel
import com.dimonkiv.savingstracker.feature.select_icon.presentation.model.toColorModels
import com.dimonkiv.savingstracker.feature.select_icon.presentation.model.toIconModels
import kotlinx.collections.immutable.toImmutableList

class SelectIconViewModel(
    reducer: SelectIconReducer
) : BaseViewModel<SelectIconIntent, SelectedIconModel, SelectIconEffect, SelectIconAction>(
    initialState = SelectedIconModel(
        icons = IconMap.icons.toIconModels().toImmutableList(),
        colors = ColorMap.colors.toColorModels().toImmutableList()
    ),
    reducer = reducer
) {

    init {
        getSelectedIcon()
    }

    override fun handleIntent(intent: SelectIconIntent) {
        when (intent) {
            is SelectIconIntent.OnColorSelected -> {
                reduce(SelectIconAction.SetColor(intent.color))
            }

            is SelectIconIntent.OnIconSelected -> {
                reduce(SelectIconAction.SetIcon(intent.icon))
            }

            is SelectIconIntent.OnSelectButtonClick -> {
                sendEffect(
                    SelectIconEffect.OpenPreviousScreenWithArgs(
                        iconRes = currentState.selectedIcon,
                        color = ColorMap.getColorName(currentState.selectedColor)
                    )
                )
            }
        }
    }


    private fun getSelectedIcon() {
        reduce(SelectIconAction.SetIcon(-1))
        reduce(SelectIconAction.SetColor(ColorMap.colors.values.first()))
    }
}
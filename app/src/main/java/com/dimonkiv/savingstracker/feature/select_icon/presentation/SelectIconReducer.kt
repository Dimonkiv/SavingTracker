package com.dimonkiv.savingstracker.feature.select_icon.presentation

import com.dimonkiv.savingstracker.core.mvi.Reducer
import com.dimonkiv.savingstracker.feature.select_icon.presentation.SelectIconContract.SelectIconAction
import com.dimonkiv.savingstracker.feature.select_icon.presentation.model.SelectedIconModel
import kotlinx.collections.immutable.toImmutableList

class SelectIconReducer : Reducer<SelectedIconModel, SelectIconAction> {
    override fun reduce(
        state: SelectedIconModel,
        action: SelectIconAction
    ): SelectedIconModel {
        return when (action) {
            is SelectIconAction.SetColor -> state.copy(
                selectedColor = action.color,
                colors = state.colors
                    .map { it.copy(selected = action.color == it.color) }
                    .toImmutableList()
            )

            is SelectIconAction.SetIcon -> state.copy(
                selectedIcon = action.iconRes,
                buttonEnabled = action.iconRes != -1,
                icons = state.icons
                    .map { it.copy(selected = action.iconRes == it.iconRes) }
                    .toImmutableList()
            )
        }
    }
}
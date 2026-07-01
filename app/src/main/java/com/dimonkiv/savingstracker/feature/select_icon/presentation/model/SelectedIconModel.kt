package com.dimonkiv.savingstracker.feature.select_icon.presentation.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.core.mvi.model.UiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class SelectedIconModel(
    val selectedColor: Color = Color.Black,
    val selectedIcon: Int = -1,
    val buttonEnabled: Boolean = false,
    val colors: ImmutableList<ColorModel> = persistentListOf(),
    val icons: ImmutableList<IconModel> = persistentListOf()
): UiState

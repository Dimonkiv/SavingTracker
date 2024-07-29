package com.dimonkiv.savingstracker.presentation.select_icon.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.domain.model.Colors
import com.dimonkiv.savingstracker.domain.model.Icon
import com.dimonkiv.savingstracker.domain.model.SelectedIcon

data class SelectedIconModel(
    val selectedColor: Color,
    val selectedIcon: Int,
    val buttonEnabled: Boolean,
    val colors: List<ColorModel>,
    val icons: List<IconModel>
)

fun SelectedIcon.asPresentation(colors: List<Colors>, icons: List<Icon>) = SelectedIconModel(
    selectedColor = color.asPresentation().color,
    selectedIcon = icon.asPresentation().iconRes,
    buttonEnabled = false,
    colors = colors.asPresentation(),
    icons = icons.asPresentation()
)

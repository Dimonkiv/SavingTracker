package com.dimonkiv.savingstracker.presentation.select_icon.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.domain.model.Colors
import com.dimonkiv.savingstracker.presentation.core.design_system.LightPurple

data class ColorModel(
    val color: Color,
    var selected: Boolean
)

fun Colors.asPresentation() = ColorModel(
    color = ColorMap.colors.getOrDefault(color, LightPurple),
    selected = false
)

fun List<Colors>.asPresentation(): List<ColorModel> {
    return map { it.asPresentation() }
}

package com.dimonkiv.savingstracker.feature.select_icon.presentation.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.designsystem.theme.LightPurple
import com.dimonkiv.savingstracker.feature.select_icon.domain.model.Colors

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

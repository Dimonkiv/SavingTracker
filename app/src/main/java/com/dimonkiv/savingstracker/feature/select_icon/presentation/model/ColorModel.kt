package com.dimonkiv.savingstracker.feature.select_icon.presentation.model

import androidx.compose.ui.graphics.Color

data class ColorModel(
    val color: Color,
    val selected: Boolean = false
)

fun Map<String, Color>.toColorModels(): List<ColorModel> =
    keys.map { key ->
        ColorModel(
            color = getValue(key)
        )
    }

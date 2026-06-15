package com.dimonkiv.savingstracker.feature.select_icon.presentation.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.designsystem.theme.Blue
import com.dimonkiv.savingstracker.designsystem.theme.Brown
import com.dimonkiv.savingstracker.designsystem.theme.DarkGreen
import com.dimonkiv.savingstracker.designsystem.theme.LightGreen
import com.dimonkiv.savingstracker.designsystem.theme.LightPurple
import com.dimonkiv.savingstracker.designsystem.theme.Orange
import com.dimonkiv.savingstracker.designsystem.theme.Pink
import com.dimonkiv.savingstracker.designsystem.theme.Scorpion

object ColorMap {
    private var LIGHT_PURPLE = "lightPurple"
    private var DARK_GREEN = "darkGreen"
    private var LIGHT_GREEN = "lightGreen"
    private var ORANGE = "orange"
    private var BROWN = "brown"
    private var BLUE = "blue"
    private var PINK = "pink"
    private var SCORPION = "scorpion"

    val colors = mapOf(
        LIGHT_PURPLE to LightPurple,
        DARK_GREEN to DarkGreen,
        LIGHT_GREEN to LightGreen,
        ORANGE to Orange,
        BROWN to Brown,
        BLUE to Blue,
        PINK to Pink,
        SCORPION to Scorpion
    )

    fun getColorName(value: Color): String {
        for (key in colors.keys) {
            if (value == colors[key]) {
                return key
            }
        }

        return ""
    }
}
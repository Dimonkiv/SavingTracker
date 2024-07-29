package com.dimonkiv.savingstracker.presentation.select_icon.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.presentation.core.design_system.Blue
import com.dimonkiv.savingstracker.presentation.core.design_system.Brown
import com.dimonkiv.savingstracker.presentation.core.design_system.DarkGreen
import com.dimonkiv.savingstracker.presentation.core.design_system.LightGreen
import com.dimonkiv.savingstracker.presentation.core.design_system.LightPurple
import com.dimonkiv.savingstracker.presentation.core.design_system.Orange
import com.dimonkiv.savingstracker.presentation.core.design_system.Pink
import com.dimonkiv.savingstracker.presentation.core.design_system.Scorpion

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
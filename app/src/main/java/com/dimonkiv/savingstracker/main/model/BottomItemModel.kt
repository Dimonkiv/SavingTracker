package com.dimonkiv.savingstracker.main.model

import com.dimonkiv.savingstracker.core.Screen

data class BottomItemModel(
    val iconRes: Int,
    val route: Screen,
    val selected: Boolean
)
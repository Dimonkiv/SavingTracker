package com.dimonkiv.savingstracker.presentation.main.model

import com.dimonkiv.savingstracker.presentation.Screen

data class BottomItemModel(
    val iconRes: Int,
    val route: Screen,
    val selected: Boolean
)
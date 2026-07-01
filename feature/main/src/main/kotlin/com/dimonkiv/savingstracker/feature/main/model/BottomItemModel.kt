package com.dimonkiv.savingstracker.feature.main.model

import com.dimonkiv.savingstracker.core.navigation.Screen

data class BottomItemModel(
    val iconRes: Int,
    val title: String,
    val route: Screen,
    val selected: Boolean
)
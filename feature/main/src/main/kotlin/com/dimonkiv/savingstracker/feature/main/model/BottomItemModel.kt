package com.dimonkiv.savingstracker.feature.main.model

import androidx.navigation3.runtime.NavKey

data class BottomItemModel(
    val iconRes: Int,
    val title: String,
    val route: NavKey,
    val selected: Boolean
)
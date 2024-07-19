package com.dimonkiv.savingstracker.presentation.main2

import com.dimonkiv.savingstracker.presentation.main2.model.BottomItemModel

data class MainState(
    val items: List<BottomItemModel>
)

sealed class MainSideEffect {
    data class Toast(val text: String): MainSideEffect()
}
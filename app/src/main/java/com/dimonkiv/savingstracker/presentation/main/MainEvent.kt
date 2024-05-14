package com.dimonkiv.savingstracker.presentation.main

sealed class MainEvent {

    data object FetchData: MainEvent()

    data class OnPageChange(val id: Int): MainEvent()

    data object OnLayoutChange: MainEvent()

    data object OnAddCardClick: MainEvent()

    data class OnAddExpenseClick(val id: Long): MainEvent()

    data class OnScreenHeightChange(val screenHeight: Int, val mainViewHeight: Int): MainEvent()

}
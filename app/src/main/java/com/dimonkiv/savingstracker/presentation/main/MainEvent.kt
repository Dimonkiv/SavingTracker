package com.dimonkiv.savingstracker.presentation.main

sealed class MainEvent {

    data object FetchData: MainEvent()

    data class OnPageChange(val id: Int): MainEvent()

    data object OnAddCardClick: MainEvent()

    data class OnAddExpenseClick(val id: Long): MainEvent()
}
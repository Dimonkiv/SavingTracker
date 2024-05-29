package com.dimonkiv.savingstracker.presentation.main

sealed class MainEvent {

    data object FetchData: MainEvent()

    data class OnPageChange(val id: Int): MainEvent()
}
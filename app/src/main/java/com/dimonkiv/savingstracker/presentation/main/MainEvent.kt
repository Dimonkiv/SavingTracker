package com.dimonkiv.savingstracker.presentation.main

sealed class MainEvent {

    data object FetchData: MainEvent()

    data class RemoveAccount(val id: Long): MainEvent()

    data class OnPageChange(val id: Int): MainEvent()
}
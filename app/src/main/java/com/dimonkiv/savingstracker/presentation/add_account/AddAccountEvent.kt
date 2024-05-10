package com.dimonkiv.savingstracker.presentation.add_account

sealed class AddAccountEvent {
    data class OnTitleChange(val title: String): AddAccountEvent()
    data class OnBalanceChange(val balance: String): AddAccountEvent()
    data object OnBackButtonClick: AddAccountEvent()
    data object OnCreateClick: AddAccountEvent()
}
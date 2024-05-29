package com.dimonkiv.savingstracker.presentation.add_account

sealed class AddAccountEvent {

    data object LoadAccount: AddAccountEvent()
    data class OnTitleChange(val title: String): AddAccountEvent()
    data class OnBalanceChange(val balance: String): AddAccountEvent()
    data object OnBackButtonClick: AddAccountEvent()
    data object OnBankButtonClick: AddAccountEvent()

    data object OnCashButtonClick: AddAccountEvent()

    data object OnInvestButtonClick: AddAccountEvent()

    data object OnCreateClick: AddAccountEvent()
}
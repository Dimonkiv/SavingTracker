package com.dimonkiv.savingstracker.presentation.account.add_account

import com.dimonkiv.savingstracker.presentation.account.add_account.account_type.model.AccountTypeModel
import com.dimonkiv.savingstracker.presentation.core.model.UiEffect
import com.dimonkiv.savingstracker.presentation.core.model.UiEvent

class AddAccountContract {
    sealed class Event: UiEvent {
        data class OnDataReceived(val colorName: String?, val iconRes: Int?): Event()
        data class OnTypeSelected(val type: AccountTypeModel): Event()
        data class OnTitleTextChanged(val title: String): Event()
        data class OnBalanceTextChanged(val balance: String): Event()
        data object OnDismissButtonClick: Event()
        data object OnSelectIconClicked: Event()
        data object OnBackButtonClicked: Event()
        data object OnCreateButtonClicked: Event()
        data object OnTypeClicked: Event()
    }

    sealed class Effect: UiEffect {
        data object OpenSelectIconScreen: Effect()
        data object OpenPreviousScreen: Effect()
        data object ShowSelectTypeSheet: Effect()
        data object HideSelectTypeSheet: Effect()
    }
}
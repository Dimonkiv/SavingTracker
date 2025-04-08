package com.dimonkiv.savingstracker.account.presentation.add_account

import com.dimonkiv.savingstracker.account.presentation.add_account.account_type.model.AccountTypeModel
import com.dimonkiv.savingstracker.core.model.UiEffect
import com.dimonkiv.savingstracker.core.model.UiEvent

sealed class Event : UiEvent {
    data class OnDataReceived(val colorName: String?, val iconRes: Int?) : Event()
    data class OnTypeSelected(val type: AccountTypeModel) : Event()
    data class OnTitleTextChanged(val title: String) : Event()
    data class OnBalanceTextChanged(val balance: String) : Event()
    data object OnDismissButtonClick : Event()
    data object OnSelectIconClicked : Event()
    data object OnBackButtonClicked : Event()
    data object OnCreateButtonClicked : Event()
    data object OnTypeClicked : Event()
}

sealed class Effect : UiEffect {
    data object OpenSelectIconScreen : Effect()
    data object OpenPreviousScreen : Effect()
    data object ShowSelectTypeSheet : Effect()
    data object HideSelectTypeSheet : Effect()
}

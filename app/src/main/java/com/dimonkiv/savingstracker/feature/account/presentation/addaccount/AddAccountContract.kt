package com.dimonkiv.savingstracker.feature.account.presentation.addaccount

import com.dimonkiv.savingstracker.core.mvi.model.UiEffect
import com.dimonkiv.savingstracker.core.mvi.model.UiEvent
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.AccountTypeModel

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

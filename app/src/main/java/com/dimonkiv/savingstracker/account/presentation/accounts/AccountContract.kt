package com.dimonkiv.savingstracker.account.presentation.accounts

import com.dimonkiv.savingstracker.account.presentation.accounts.model.AccountsModel
import com.dimonkiv.savingstracker.core.model.UiEffect
import com.dimonkiv.savingstracker.core.model.UiEvent
import com.dimonkiv.savingstracker.core.model.UiState

sealed class Event : UiEvent {
    data object LoadAccounts : Event()
    data object OnErrorDialogClick : Event()
}

sealed class Effect : UiEffect

sealed class AccountState : UiState {
    data object Idle : AccountState()
    data object Loading : AccountState()
    data class Success(val model: AccountsModel) : AccountState()
    data class Error(val message: String) : AccountState()
}
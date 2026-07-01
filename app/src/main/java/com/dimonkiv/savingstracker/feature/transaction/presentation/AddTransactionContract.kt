package com.dimonkiv.savingstracker.feature.transaction.presentation

import com.dimonkiv.savingstracker.core.mvi.model.UiAction
import com.dimonkiv.savingstracker.core.mvi.model.UiEffect
import com.dimonkiv.savingstracker.core.mvi.model.UiIntent
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.AddTransactionUiModel
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.TransactionAccountModel

sealed class Intent : UiIntent {
    data class OnBalanceTextChanged(val balance: String): Intent()
    data class OnNoteTextChanged(val note: String): Intent()
    data class OnDateChanged(val timestamp: Long): Intent()
    data class OnAccountSelected(val account: TransactionAccountModel): Intent()
    data object OnSaveClicked: Intent()
    data object OnBackClicked: Intent()
}

sealed class Effect : UiEffect {
    data class ShowError(val message: String): Effect()
    data object NavigateBack: Effect()
}

sealed interface AddTransactionAction: UiAction {
    data class SetInitialData(val model: AddTransactionUiModel): AddTransactionAction
    data class SetBalance(val balance: String): AddTransactionAction
    data class SetNote(val note: String): AddTransactionAction
    data class SetDate(val timestamp: Long): AddTransactionAction
    data class SetSelectedAccount(val account: TransactionAccountModel): AddTransactionAction
}
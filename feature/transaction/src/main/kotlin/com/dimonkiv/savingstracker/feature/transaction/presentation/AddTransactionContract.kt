package com.dimonkiv.savingstracker.feature.transaction.presentation

import com.dimonkiv.savingstracker.core.mvi.model.UiAction
import com.dimonkiv.savingstracker.core.mvi.model.UiEffect
import com.dimonkiv.savingstracker.core.mvi.model.UiIntent
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.AddTransactionUiModel
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.TransactionAccountModel

sealed class AddTransactionIntent : UiIntent {
    data class OnBalanceTextChanged(val balance: String): AddTransactionIntent()
    data class OnNoteTextChanged(val note: String): AddTransactionIntent()
    data class OnDateChanged(val timestamp: Long): AddTransactionIntent()
    data class OnAccountSelected(val account: TransactionAccountModel): AddTransactionIntent()
    data object OnSaveClicked: AddTransactionIntent()
    data object OnBackClicked: AddTransactionIntent()
}

sealed class AddTransactionEffect : UiEffect {
    data class ShowError(val message: String): AddTransactionEffect()
    data object NavigateBack: AddTransactionEffect()
}

sealed interface AddTransactionAction: UiAction {
    data class SetInitialData(val model: AddTransactionUiModel): AddTransactionAction
    data class SetBalance(val balance: String): AddTransactionAction
    data class SetNote(val note: String): AddTransactionAction
    data class SetDate(val timestamp: Long): AddTransactionAction
    data class SetSelectedAccount(val account: TransactionAccountModel): AddTransactionAction
}
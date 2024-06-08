package com.dimonkiv.savingstracker.presentation.transaction

sealed class TransactionUiEvent {
    data object PopBackStack: TransactionUiEvent()

    data class ShowMessage(val message: String): TransactionUiEvent()

}
package com.dimonkiv.savingstracker.presentation.transaction

sealed class TransactionEvent {

    data object LoadData: TransactionEvent()

    data class OnTitleChange(val title: String): TransactionEvent()

    data class OnExpenseChange(val expense: String): TransactionEvent()

    data class OnExpenseSelected(val isChecked: Boolean): TransactionEvent()

    data class OnIncomeSelected(val isChecked: Boolean): TransactionEvent()

    data object OnCreateClick: TransactionEvent()
}
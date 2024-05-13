package com.dimonkiv.savingstracker.presentation.add_expense

sealed class AddExpenseUiEvent {
    data object PopBackStack: AddExpenseUiEvent()

    data class ShowMessage(val message: String): AddExpenseUiEvent()

}
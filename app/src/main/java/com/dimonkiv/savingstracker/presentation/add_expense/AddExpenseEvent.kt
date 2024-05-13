package com.dimonkiv.savingstracker.presentation.add_expense

sealed class AddExpenseEvent {

    data class OnTitleChange(val title: String): AddExpenseEvent()

    data class OnExpenseChange(val expense: String): AddExpenseEvent()

    data class OnExpenseSelected(val isChecked: Boolean): AddExpenseEvent()

    data class OnIncomeSelected(val isChecked: Boolean): AddExpenseEvent()

    data object OnCreateClick: AddExpenseEvent()
}
package com.dimonkiv.savingstracker.presentation.add_expense

data class AddExpenseState(
    var title: String = "",
    var titleError: String? = null,
    var expense: String = "",
    var expenseError: String? = null,
    var isExpense: Boolean = true,
    var isIncome: Boolean = false
)

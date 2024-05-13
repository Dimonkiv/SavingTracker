package com.dimonkiv.savingstracker.domain

import com.dimonkiv.savingstracker.domain.model.Expense

interface ExpenseRepository {

    fun fetchExpensesById(id: Long): List<Expense>

    fun addExpense(expense: Expense)
}
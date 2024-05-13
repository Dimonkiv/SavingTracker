package com.dimonkiv.savingstracker.data

import com.dimonkiv.savingstracker.domain.ExpenseRepository
import com.dimonkiv.savingstracker.domain.model.Expense

class ExpenseRepositoryImpl: ExpenseRepository {

    private val items = mutableListOf<Expense>()

    override fun fetchExpensesById(id: Long): List<Expense> {
        return items.filter { it.accountId == id }
    }

    override fun addExpense(expense: Expense) {
        items.add(expense)
    }
}
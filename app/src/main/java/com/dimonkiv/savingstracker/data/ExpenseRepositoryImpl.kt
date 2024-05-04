package com.dimonkiv.savingstracker.data

import com.dimonkiv.savingstracker.domain.ExpenseRepository
import com.dimonkiv.savingstracker.domain.model.Expense

class ExpenseRepositoryImpl: ExpenseRepository {
    override fun fetchExpensesById(id: Long): List<Expense> {
        val items = generateExpenses()

        return items.filter { it.accountId == id }
    }

    private fun generateExpenses(): List<Expense> {
        val items = mutableListOf<Expense>()

        items.add(Expense("Savings", 1000, "Today, 12:00", 1, false))
        items.add(Expense("Expense", 2000, "Today, 11:00", 1, true))
        items.add(Expense("Savings", 500, "April 27, 1:00", 1, false))
        items.add(Expense("Savings", 1500, "April 26, 1:00", 1, false))

        items.add(Expense("Savings", 3000, "Today, 12:00", 2, false))
        items.add(Expense("Expense", 1000, "April 28, 1:00", 2, true))
        items.add(Expense("Savings", 2000, "April 27, 1:00", 2, false))

        return items
    }
}
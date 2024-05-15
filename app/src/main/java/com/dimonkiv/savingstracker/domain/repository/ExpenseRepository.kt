package com.dimonkiv.savingstracker.domain.repository

import com.dimonkiv.savingstracker.domain.model.Expense

interface ExpenseRepository {

    suspend fun getAllExpenses(): List<Expense>

    suspend fun getExpenseById(id: Long): List<Expense>

    suspend fun getExpenseByAccountId(accountId: Long): List<Expense>

    suspend fun insertExpense(expense: Expense)

    suspend fun updateExpense(expense: Expense)
}
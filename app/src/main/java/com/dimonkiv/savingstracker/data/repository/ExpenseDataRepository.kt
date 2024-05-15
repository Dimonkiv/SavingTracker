package com.dimonkiv.savingstracker.data.repository

import com.dimonkiv.savingstracker.data.local.dao.ExpenseDao
import com.dimonkiv.savingstracker.data.local.dto.asDTO
import com.dimonkiv.savingstracker.data.local.dto.asDomain
import com.dimonkiv.savingstracker.domain.repository.ExpenseRepository
import com.dimonkiv.savingstracker.domain.model.Expense
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExpenseDataRepository @Inject constructor(
    private val expenseDao: ExpenseDao,
    private val dispatcher: CoroutineDispatcher
) : ExpenseRepository {
    override suspend fun getAllExpenses(): List<Expense> = withContext(dispatcher) {
        expenseDao.getAllExpenses().asDomain()
    }

    override suspend fun getExpenseById(id: Long): List<Expense> = withContext(dispatcher) {
        expenseDao.getExpenseById(id).asDomain()
    }

    override suspend fun getExpenseByAccountId(accountId: Long): List<Expense> =
        withContext(dispatcher) {
            expenseDao.getExpenseByAccountId(accountId).asDomain()
        }

    override suspend fun insertExpense(expense: Expense) = withContext(dispatcher) {
        expenseDao.insertExpense(expense.asDTO())
    }

    override suspend fun updateExpense(expense: Expense) = withContext(dispatcher) {
        expenseDao.updateExpense(expense.asDTO())
    }
}
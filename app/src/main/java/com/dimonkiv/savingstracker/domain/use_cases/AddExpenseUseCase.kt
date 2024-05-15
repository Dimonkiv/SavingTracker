package com.dimonkiv.savingstracker.domain.use_cases

import com.dimonkiv.savingstracker.domain.model.Expense

fun interface AddExpenseUseCase {
    suspend fun invoke(expense: Expense)
}
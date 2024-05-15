package com.dimonkiv.savingstracker.domain.use_cases

import com.dimonkiv.savingstracker.domain.model.Expense
import com.dimonkiv.savingstracker.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.domain.repository.ExpenseRepository
import javax.inject.Inject

class AddExpenseUseCaseImpl @Inject constructor(
    private val expenseRepository: ExpenseRepository,
    private val accountRepository: AccountRepository
) : AddExpenseUseCase {
    override suspend fun invoke(expense: Expense) {
        expenseRepository.insertExpense(expense)
        updateAccount(expense)
    }

    private suspend fun updateAccount(expense: Expense) {
        val account = accountRepository.fetchAccountById(expense.accountId)
        account.balance = if (expense.isExpense) {
            account.balance - expense.value
        } else {
            account.balance + expense.value
        }

        accountRepository.updateAccount(account)
    }
}
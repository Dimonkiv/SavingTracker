package com.dimonkiv.savingstracker.domain.use_cases.transaction

import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.domain.model.Account
import com.dimonkiv.savingstracker.domain.model.AccountType
import com.dimonkiv.savingstracker.domain.model.TransactionType
import com.dimonkiv.savingstracker.domain.use_cases.GetAccountsUseCase
import com.dimonkiv.savingstracker.presentation.transaction.TransactionState
import com.dimonkiv.savingstracker.presentation.transaction.TransactionTypeState
import com.dimonkiv.savingstracker.presentation.utils.DateUtils
import com.dimonkiv.savingstracker.presentation.utils.ResourceManager
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor(
    private var getAccountsUseCase: GetAccountsUseCase,
    private var resourceManager: ResourceManager
) {
    suspend fun invoke(): TransactionState {
        val accounts = getAccountsUseCase.invoke()
            .filter { it.type.type != AccountType.Type.DEFAULT }
        val incomeId = 0L
        val expenseId = 0L

        val income = getAccountById(accounts, incomeId)
        val expense = getAccountById(accounts, expenseId)


        return TransactionState().apply {
            transactions = createTransactionItems(income, expense, accounts)
        }
    }

    private fun createTransactionItems(
        income: Account?,
        expense: Account?,
        accounts: List<Account>
    ): List<TransactionTypeState> {
        val states = mutableListOf<TransactionTypeState>()
        val expenseItem = expense ?: accounts[0]

        states.add(createIncomeState(expenseItem))
        states.add(createExpenseState(expenseItem))

        if (accounts.size > 1) {
            val incomeItem = income ?: accounts[1]
            states.add(createTransferState(expenseItem, incomeItem))
        }


        return states
    }

    private fun getAccountById(accounts: List<Account>, id: Long): Account? {
        return accounts.find { it.id == id }
    }

    private fun createIncomeState(account: Account): TransactionTypeState {
        return TransactionTypeState().apply {
            expenseAccountId = account.id
            expenseTitle = account.name
            expenseRes = getAccountColor(account.type.type)
            expenseBalance = "$ ${account.balance}"
            date = DateUtils.getCurrentDate()
            typeStr = resourceManager.getString(R.string.income)
            type = TransactionType.INCOME
        }
    }

    private fun createExpenseState(account: Account): TransactionTypeState {
        return TransactionTypeState().apply {
            expenseAccountId = account.id
            expenseTitle = account.name
            expenseRes = getAccountColor(account.type.type)
            expenseBalance = "$ ${account.balance}"
            date = DateUtils.getCurrentDate()
            typeStr = resourceManager.getString(R.string.expense)
            type = TransactionType.EXPENSE
        }
    }

    private fun createTransferState(expense: Account, income: Account): TransactionTypeState {
        return TransactionTypeState().apply {
            expenseAccountId = expense.id
            expenseTitle = expense.name
            expenseRes = getAccountColor(expense.type.type)
            expenseBalance = "$ ${expense.balance}"
            incomeAccountId = income.id
            incomeTitle = income.name
            incomeRes = getAccountColor(income.type.type)
            incomeBalance = "$ ${income.balance}"
            date = DateUtils.getCurrentDate()
            typeStr = resourceManager.getString(R.string.transfer)
            type = TransactionType.TRANSFER
        }
    }

    private fun getAccountColor(type: AccountType.Type): Int {
        return when (type) {
            AccountType.Type.CASH -> R.drawable.bg_cash_selected
            AccountType.Type.INVEST -> R.drawable.bg_invest_selected
            else -> R.drawable.bg_bank_selected
        }
    }
}
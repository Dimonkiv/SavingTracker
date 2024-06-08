package com.dimonkiv.savingstracker.domain.use_cases.transaction

import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.domain.model.TransactionType
import com.dimonkiv.savingstracker.presentation.transaction.TransactionState
import com.dimonkiv.savingstracker.presentation.transaction.TransactionTypeState
import com.dimonkiv.savingstracker.presentation.utils.DateUtils
import com.dimonkiv.savingstracker.presentation.utils.ResourceManager
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor(
    private var resourceManager: ResourceManager
) {
    fun invoke(): TransactionState {
        return TransactionState().apply {
            transactions = listOf(createIncomeState(), createExpenseState(), createTransferState())
        }
    }

    private fun createIncomeState(): TransactionTypeState {
        return TransactionTypeState().apply {
            incomeTitle = resourceManager.getString(R.string.bank_account)
            incomeAccountResId = R.drawable.bg_bank_selected
            date = DateUtils.getCurrentDate()
            type = TransactionType.INCOME
        }
    }

    private fun createExpenseState(): TransactionTypeState {
        return TransactionTypeState().apply {
            expenseTitle = resourceManager.getString(R.string.bank_account)
            expenseAccountResId = R.drawable.bg_bank_selected
            date = DateUtils.getCurrentDate()
            type = TransactionType.EXPENSE
        }
    }

    private fun createTransferState(): TransactionTypeState {
        return TransactionTypeState().apply {
            expenseTitle = resourceManager.getString(R.string.bank_account)
            expenseAccountResId = R.drawable.bg_bank_selected
            incomeTitle = resourceManager.getString(R.string.cash)
            incomeAccountResId = R.drawable.bg_cash_selected
            date = DateUtils.getCurrentDate()
            type = TransactionType.TRANSFER
        }
    }
}
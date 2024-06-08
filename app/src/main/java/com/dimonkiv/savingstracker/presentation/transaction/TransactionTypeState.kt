package com.dimonkiv.savingstracker.presentation.transaction

import com.dimonkiv.savingstracker.domain.model.TransactionType

data class TransactionTypeState(
    var title: String = "",
    var titleErr: String? = null,
    var incomeAccountId: Long = 0,
    var incomeAccountResId: Int = 0,
    var incomeTitle: String = "",
    var expenseAccountId: Long = 0,
    var expenseAccountResId: Int = 0,
    var expenseTitle: String = "",
    var date: String = "",
    var type: TransactionType = TransactionType.INCOME
)
package com.dimonkiv.savingstracker.presentation.transaction

import com.dimonkiv.savingstracker.domain.model.TransactionType

data class TransactionTypeState(
    var title: String = "",
    var titleErr: String? = null,
    var incomeAccountId: Long = 0,
    var incomeBalance: String = "",
    var incomeTitle: String = "",
    var incomeRes: Int = 0,
    var expenseAccountId: Long = 0,
    var expenseBalance: String = "",
    var expenseTitle: String = "",
    var expenseRes: Int = 0,
    var date: String = "",
    var typeStr: String = "",
    var type: TransactionType = TransactionType.INCOME
)
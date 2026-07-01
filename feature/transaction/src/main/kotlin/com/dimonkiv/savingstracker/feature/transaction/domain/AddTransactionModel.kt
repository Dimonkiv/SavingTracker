package com.dimonkiv.savingstracker.feature.transaction.domain

import com.dimonkiv.savingstracker.core.data.accountapi.model.Account

data class AddTransactionModel(
    val title: String = "",
    val balance: String = "",
    val note: String = "",
    val timestamp: Long = -1,
    val selectedType: TransactionType = TransactionType.EXPENSE,
    val types: List<TransactionType> = emptyList(),
    val accounts: List<Account> = emptyList(),
    val selectedAccount: Account? = null
)

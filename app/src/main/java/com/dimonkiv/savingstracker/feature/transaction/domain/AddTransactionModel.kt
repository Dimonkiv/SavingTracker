package com.dimonkiv.savingstracker.feature.transaction.domain

import androidx.compose.runtime.Immutable
import com.dimonkiv.savingstracker.feature.account.domain.model.Account
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.AddTransactionType

@Immutable
data class AddTransactionModel(
    val title: String = "",
    val balance: String = "",
    val note: String = "",
    val timestamp: Long = -1,
    val selectedType: AddTransactionType = AddTransactionType.EXPENSE,
    val types: List<AddTransactionType> = mutableListOf(),
    val accounts: List<Account> = mutableListOf(),
    val selectedAccount: Account? = null
)

package com.dimonkiv.savingstracker.feature.account.domain.model


data class AccountsResult(
    val totalBalance: String = "",
    val types: List<AccountTypeWithAccounts> = emptyList(),
)

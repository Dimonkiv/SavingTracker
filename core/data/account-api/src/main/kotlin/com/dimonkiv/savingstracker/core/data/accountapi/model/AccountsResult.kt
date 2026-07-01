package com.dimonkiv.savingstracker.core.data.accountapi.model


data class AccountsResult(
    val totalBalance: String = "",
    val types: List<AccountTypeWithAccounts> = emptyList(),
)

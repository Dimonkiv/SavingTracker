package com.dimonkiv.savingstracker.core.data.accountapi.model

data class AccountTypeWithAccounts(
    val type: AccountType,
    val accounts: List<Account>
)

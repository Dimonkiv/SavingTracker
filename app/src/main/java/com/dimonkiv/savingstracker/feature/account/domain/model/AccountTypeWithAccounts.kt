package com.dimonkiv.savingstracker.feature.account.domain.model

data class AccountTypeWithAccounts(
    val type: AccountType,
    val accounts: List<Account>
)

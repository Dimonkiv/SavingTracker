package com.dimonkiv.savingstracker.account.presentation.accounts.model

data class AccountsModel(
    val totalBalance: String,
    val types: List<TypesModel>
)

package com.dimonkiv.savingstracker.feature.account.presentation.accounts.model

data class AccountsModel(
    val totalBalance: String,
    val types: List<TypesModel>
)

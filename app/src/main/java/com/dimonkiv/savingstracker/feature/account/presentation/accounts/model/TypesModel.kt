package com.dimonkiv.savingstracker.feature.account.presentation.accounts.model

import androidx.compose.ui.graphics.Color

data class TypesModel(
    val id: Long,
    val title: String,
    val color: Color,
    val accounts: List<AccountModel>
)

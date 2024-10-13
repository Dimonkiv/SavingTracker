package com.dimonkiv.savingstracker.presentation.account.add_account.model

import androidx.compose.ui.graphics.Color

data class AddAccountModel(
    val color: Color,
    val iconRes: Int,
    val type: AccountTypeModel,
    val types: List<AccountTypeModel>,
    val title: String,
    val balance: String
)
package com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.designsystem.theme.Dark
import com.dimonkiv.savingstracker.core.data.accountapi.model.AccountType
import com.dimonkiv.savingstracker.designsystem.theme.ColorMap

data class AccountTypeModel(
    val id: Long = 0,
    val title: String = "",
    val color: Color = Dark
)

fun AccountType.asPresentation() = AccountTypeModel(
    id = id,
    title = title,
    color = ColorMap.colors.getOrDefault(color, Color.White)
)

fun List<AccountType>.asPresentation() = map { it.asPresentation() }

fun AccountTypeModel.asDomain() = AccountType(
    id = id,
    title = title,
    color = ColorMap.getColorName(color)
)

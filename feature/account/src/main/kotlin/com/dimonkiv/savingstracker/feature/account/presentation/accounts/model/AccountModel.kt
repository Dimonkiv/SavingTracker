package com.dimonkiv.savingstracker.feature.account.presentation.accounts.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.core.designsystem.R
import com.dimonkiv.savingstracker.core.data.accountapi.model.Account
import com.dimonkiv.savingstracker.designsystem.theme.ColorMap
import com.dimonkiv.savingstracker.designsystem.theme.IconMap

data class  AccountModel(
    val id: Long,
    val typeId: Long,
    val title: String,
    val balance: String,
    val color: Color,
    val iconRes: Int
)

fun Account.asPresentation() = AccountModel(
    id = id,
    typeId = typeId,
    title = title,
    balance = "$$balance",
    color = ColorMap.colors.getOrDefault(color, Color.White),
    iconRes = IconMap.icons.getOrDefault(icon, R.drawable.ic_money_bag)
)

fun List<Account>.asPresentation(): List<AccountModel> {
    return map {it.asPresentation()}
}
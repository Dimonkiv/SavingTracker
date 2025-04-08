package com.dimonkiv.savingstracker.account.presentation.accounts.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.account.domain.model.Account
import com.dimonkiv.savingstracker.select_icon.presentation.model.ColorMap
import com.dimonkiv.savingstracker.select_icon.presentation.model.IconMap

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
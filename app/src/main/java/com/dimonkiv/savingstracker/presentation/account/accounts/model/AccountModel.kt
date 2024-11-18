package com.dimonkiv.savingstracker.presentation.account.accounts.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.domain.model.Account
import com.dimonkiv.savingstracker.presentation.select_icon.model.ColorMap
import com.dimonkiv.savingstracker.presentation.select_icon.model.IconMap

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
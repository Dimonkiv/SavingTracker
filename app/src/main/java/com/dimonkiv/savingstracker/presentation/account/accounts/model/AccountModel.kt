package com.dimonkiv.savingstracker.presentation.account.accounts.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.domain.model.Account

data class AccountModel(
    val id: Long,
    val title: String,
    val balance: String,
    val color: Color,
    val iconRes: Int
)

fun Account.asPresentation() = AccountModel(
    id = id,
    title = title,
    balance = balance.toString(),
    color = AccountsColorMap.colors.getOrDefault(color, Color.White),
    iconRes = AccountsIconsMap.icons.getOrDefault(icon, R.drawable.ic_money_bag)
)

fun List<Account>.asPresentation(): List<AccountModel> {
    return map {it.asPresentation()}
}
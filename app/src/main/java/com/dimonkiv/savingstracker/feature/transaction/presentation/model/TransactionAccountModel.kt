package com.dimonkiv.savingstracker.feature.transaction.presentation.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.designsystem.theme.ColorMap
import com.dimonkiv.savingstracker.designsystem.theme.IconMap
import com.dimonkiv.savingstracker.feature.account.domain.model.Account

data class TransactionAccountModel(
    val id: Long,
    val title: String,
    val color: Color,
    val iconRes: Int
)

fun Account.toTransactionModel() = TransactionAccountModel(
    id = id,
    title = title,
    color = ColorMap.colors.getOrDefault(color, Color.White),
    iconRes = IconMap.icons.getOrDefault(icon, R.drawable.ic_money_bag)
)

fun List<Account>.toTransactionModels() = map { it.toTransactionModel() }

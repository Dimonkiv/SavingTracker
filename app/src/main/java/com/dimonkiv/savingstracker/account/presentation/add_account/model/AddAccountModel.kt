package com.dimonkiv.savingstracker.account.presentation.add_account.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.account.domain.model.Account
import com.dimonkiv.savingstracker.account.presentation.add_account.account_type.model.AccountTypeModel
import com.dimonkiv.savingstracker.core.model.UiState
import com.dimonkiv.savingstracker.select_icon.presentation.model.ColorMap
import com.dimonkiv.savingstracker.select_icon.presentation.model.IconMap

data class AddAccountModel(
    val color: Color,
    val iconRes: Int,
    val type: AccountTypeModel,
    val title: String,
    val balance: String,
    val showBottomSheet: Boolean,
    val isButtonEnabled: Boolean
) : UiState

val AddAccountModel.isNotEmpty
    get() = iconRes != -1 &&
            type.title.isNotEmpty() &&
            title.isNotEmpty() &&
            balance.isNotEmpty()

fun AddAccountModel.asDomain() = Account(
    id = 0L,
    typeId = type.id,
    title = title,
    balance = balance.toIntOrNull() ?: 0,
    color = ColorMap.getColorName(color),
    icon = IconMap.getIconName(iconRes)
)
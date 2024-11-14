package com.dimonkiv.savingstracker.presentation.account.add_account.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.domain.model.Account
import com.dimonkiv.savingstracker.presentation.account.add_account.account_type.model.AccountTypeModel
import com.dimonkiv.savingstracker.presentation.core.model.UiState
import com.dimonkiv.savingstracker.presentation.select_icon.model.ColorMap
import com.dimonkiv.savingstracker.presentation.select_icon.model.IconMap

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
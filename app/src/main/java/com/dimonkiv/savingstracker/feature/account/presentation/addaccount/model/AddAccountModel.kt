package com.dimonkiv.savingstracker.feature.account.presentation.addaccount.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.core.mvi.model.UiState
import com.dimonkiv.savingstracker.designsystem.theme.Dark
import com.dimonkiv.savingstracker.feature.account.domain.model.Account
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.AccountTypeModel
import com.dimonkiv.savingstracker.designsystem.theme.ColorMap
import com.dimonkiv.savingstracker.designsystem.theme.IconMap

data class AddAccountModel(
    val color: Color = Dark,
    val iconRes: Int = -1,
    val type: AccountTypeModel = AccountTypeModel(),
    val title: String = "",
    val balance: String = "",
    val showBottomSheet: Boolean = false,
    val isButtonEnabled: Boolean = false
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
    balance = balance.toLongOrNull() ?: 0L,
    color = ColorMap.getColorName(color),
    icon = IconMap.getIconName(iconRes)
)
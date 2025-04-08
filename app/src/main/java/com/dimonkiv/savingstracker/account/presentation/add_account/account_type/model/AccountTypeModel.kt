package com.dimonkiv.savingstracker.account.presentation.add_account.account_type.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.account.data.local.dto.AccountTypeDTO
import com.dimonkiv.savingstracker.select_icon.presentation.model.ColorMap

data class AccountTypeModel(
    val id: Long,
    val title: String,
    val color: Color
)

fun AccountTypeDTO.asPresentation() = AccountTypeModel(
    id = id,
    title = title,
    color = ColorMap.colors.getOrDefault(color, Color.White)
)

fun List<AccountTypeDTO>.asPresentation() = map { it.asPresentation() }

fun AccountTypeModel.asDTO() = AccountTypeDTO(
    id = id,
    title = title,
    color = ColorMap.getColorName(color)
)

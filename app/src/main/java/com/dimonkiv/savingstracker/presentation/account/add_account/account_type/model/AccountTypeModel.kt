package com.dimonkiv.savingstracker.presentation.account.add_account.account_type.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.data.local.dto.AccountTypeDTO
import com.dimonkiv.savingstracker.presentation.select_icon.model.ColorMap

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

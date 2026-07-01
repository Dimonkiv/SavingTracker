package com.dimonkiv.savingstracker.feature.account.presentation.accounts.model

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.feature.account.domain.model.AccountTypeWithAccounts
import com.dimonkiv.savingstracker.designsystem.theme.ColorMap
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

data class TypesWithAccountsModel(
    val id: Long = 0,
    val title: String = "",
    val color: Color,
    val accounts: ImmutableList<AccountModel> = persistentListOf()
)

fun AccountTypeWithAccounts.asUi() = TypesWithAccountsModel(
    id = type.id,
    title = type.title,
    color = ColorMap.colors.getOrDefault(type.color, Color.White),
    accounts = accounts.asPresentation().toImmutableList()
)

fun List<AccountTypeWithAccounts>.asUi() = map { it.asUi()}

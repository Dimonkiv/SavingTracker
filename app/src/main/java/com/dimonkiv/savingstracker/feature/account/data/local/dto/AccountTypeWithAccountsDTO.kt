package com.dimonkiv.savingstracker.feature.account.data.local.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.dimonkiv.savingstracker.feature.account.domain.model.AccountTypeWithAccounts

data class AccountTypeWithAccountsDTO(
    @Embedded
    val accountType: AccountTypeDTO,
    @Relation(
        parentColumn = "id",
        entityColumn = "type_id"
    )
    val accounts: List<AccountDTO>
)


fun AccountTypeWithAccountsDTO.asDomain() = AccountTypeWithAccounts(
    type = accountType.asDomain(),
    accounts = accounts.asDomain()
)

fun List<AccountTypeWithAccountsDTO>.asDomain() = map { it.asDomain() }
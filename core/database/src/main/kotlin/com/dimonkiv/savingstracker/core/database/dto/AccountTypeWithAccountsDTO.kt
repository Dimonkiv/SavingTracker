package com.dimonkiv.savingstracker.core.database.dto

import androidx.room.Embedded
import androidx.room.Relation
import com.dimonkiv.savingstracker.core.data.accountapi.model.AccountTypeWithAccounts

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
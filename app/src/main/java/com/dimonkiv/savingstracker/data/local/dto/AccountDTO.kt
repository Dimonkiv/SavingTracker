package com.dimonkiv.savingstracker.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dimonkiv.savingstracker.domain.model.Account
import com.dimonkiv.savingstracker.presentation.add_account.AccountType

@Entity(tableName = "account")
data class AccountDTO(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "balance")
    val balance: Int,
    @ColumnInfo(name = "type")
    val type: String
)

fun AccountDTO.asDomain() = Account(
    id = id,
    name = title,
    balance = balance,
    type = AccountType(type = AccountType.Type.valueOf(type))
)

fun Account.asDTO() = AccountDTO(
    id = id,
    title = name,
    balance = balance,
    type = type.type.name
)

fun List<AccountDTO>.asDomain(): List<Account> {
    return map { it.asDomain() }
}

fun List<Account>.asDTO(): List<AccountDTO> {
    return map { it.asDTO() }
}


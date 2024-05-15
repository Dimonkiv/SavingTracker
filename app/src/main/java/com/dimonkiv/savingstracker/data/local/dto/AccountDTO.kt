package com.dimonkiv.savingstracker.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dimonkiv.savingstracker.domain.model.Account
import com.dimonkiv.savingstracker.domain.model.AccountType

@Entity(tableName = "account")
data class AccountDTO(
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "balance")
    val balance: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

fun AccountDTO.asDomain() = Account(
    id = id,
    name = title,
    balance = balance,
    type = AccountType.CREATED
)

fun Account.asDTO() = AccountDTO(
    title = name,
    balance = balance
)

fun List<AccountDTO>.asDomain(): List<Account> {
    return map { it.asDomain() }
}

fun List<Account>.asDTO(): List<AccountDTO> {
    return map { it.asDTO() }
}


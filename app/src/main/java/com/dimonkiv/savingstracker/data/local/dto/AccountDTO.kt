package com.dimonkiv.savingstracker.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dimonkiv.savingstracker.domain.model.Account

@Entity(tableName = "account")
data class AccountDTO(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "balance")
    val balance: Int,
    @ColumnInfo(name = "color")
    val color: String,
    @ColumnInfo(name = "icon")
    val icon: String
)

fun AccountDTO.asDomain() = Account(
    id = id,
    title = title,
    balance = balance,
    color = color,
    icon = icon
)

fun List<AccountDTO>.asDomain(): List<Account> {
    return map { it.asDomain() }
}


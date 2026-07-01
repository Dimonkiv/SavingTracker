package com.dimonkiv.savingstracker.core.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.dimonkiv.savingstracker.core.data.accountapi.model.Account

@Entity(
    tableName = "account",
    foreignKeys = [
        ForeignKey(
            entity = AccountTypeDTO::class,
            parentColumns = ["id"],
            childColumns = ["type_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class AccountDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "type_id", index = true)
    val typeId: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "balance")
    val balance: Long,
    @ColumnInfo(name = "color")
    val color: String,
    @ColumnInfo(name = "icon")
    val icon: String
)

fun AccountDTO.asDomain() = Account(
    id = id,
    typeId = typeId,
    title = title,
    balance = balance,
    color = color,
    icon = icon
)

fun Account.asDTO() = AccountDTO(
    typeId = typeId,
    title = title,
    balance = balance,
    color = color,
    icon = icon
)

fun List<AccountDTO>.asDomain(): List<Account> {
    return map { it.asDomain() }
}


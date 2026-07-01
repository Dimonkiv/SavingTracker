package com.dimonkiv.savingstracker.core.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dimonkiv.savingstracker.core.data.accountapi.model.AccountType

@Entity(tableName = "account_type")
data class AccountTypeDTO(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "color")
    val color: String
)

fun AccountTypeDTO.asDomain() = AccountType(
    id = id,
    title = title,
    color = color
)

fun List<AccountTypeDTO>.asDomain() = map { it.asDomain() }

fun AccountType.asDto() = AccountTypeDTO(
    title = title,
    color = color
)


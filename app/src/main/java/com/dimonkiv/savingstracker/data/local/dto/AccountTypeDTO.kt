package com.dimonkiv.savingstracker.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_type")
data class AccountTypeDTO(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "color")
    val color: String
)


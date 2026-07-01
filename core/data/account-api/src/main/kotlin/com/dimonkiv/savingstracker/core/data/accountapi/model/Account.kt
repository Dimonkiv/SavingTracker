package com.dimonkiv.savingstracker.core.data.accountapi.model

data class Account(
    val id: Long = 0,
    val typeId: Long = 0,
    val title: String = "",
    val balance: Long = 0L,
    val color: String,
    val icon: String
)
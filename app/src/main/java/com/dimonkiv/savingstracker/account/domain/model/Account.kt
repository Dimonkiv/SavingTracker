package com.dimonkiv.savingstracker.account.domain.model

data class Account(
    val id: Long = 0,
    val typeId: Long = 0,
    val title: String = "",
    val balance: Int = 0,
    val color: String,
    val icon: String
)
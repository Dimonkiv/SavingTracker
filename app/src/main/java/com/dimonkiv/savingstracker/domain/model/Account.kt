package com.dimonkiv.savingstracker.domain.model

data class Account(
    val id: Long = 0,
    val title: String = "",
    val balance: Int = 0,
    val color: String,
    val icon: String
)
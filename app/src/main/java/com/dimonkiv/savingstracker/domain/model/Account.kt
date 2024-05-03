package com.dimonkiv.savingstracker.domain.model

data class Account(
    var id: Long = 0,
    var name: String = "",
    var balance: Int = 0
)
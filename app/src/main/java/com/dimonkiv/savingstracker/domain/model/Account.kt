package com.dimonkiv.savingstracker.domain.model

data class Account(
    var id: Long = 0,
    var name: String = "",
    var balance: Int = 0,
    var type: AccountType = AccountType.DEFAULT
)
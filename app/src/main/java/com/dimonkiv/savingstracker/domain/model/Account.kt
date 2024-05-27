package com.dimonkiv.savingstracker.domain.model

import com.dimonkiv.savingstracker.presentation.add_account.AccountType

data class Account(
    var id: Long = 0,
    var name: String = "",
    var balance: Int = 0,
    var type: AccountType = AccountType(type = AccountType.Type.DEFAULT)
)
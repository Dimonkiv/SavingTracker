package com.dimonkiv.savingstracker.presentation.add_account

import com.dimonkiv.savingstracker.domain.model.AccountType

data class AddAccountFormState(
    var title: String = "",
    var titleError: String? = null,
    var balance: String = "",
    var type: AccountType = AccountType()
)

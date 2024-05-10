package com.dimonkiv.savingstracker.presentation.add_account

data class AddAccountFormState(
    var title: String = "",
    var titleError: String? = null,
    var balance: String = ""
)

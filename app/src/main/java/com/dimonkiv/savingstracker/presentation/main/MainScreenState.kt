package com.dimonkiv.savingstracker.presentation.main

import com.dimonkiv.savingstracker.domain.model.Account
import com.dimonkiv.savingstracker.domain.model.Expense

data class MainScreenState(
    var accounts: List<Account> = emptyList(),
    var expenses: List<Expense> = emptyList(),
    var totalBalance: String = "",
    var openedAccount: Int = 0
)

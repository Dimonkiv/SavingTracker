package com.dimonkiv.savingstracker.domain.model

data class Expense(
    var title: String = "",
    var value: Int = 0,
    var date: String = "",
    var accountId: Long = 0L,
    var isExpense: Boolean = false
)
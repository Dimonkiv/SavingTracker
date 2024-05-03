package com.dimonkiv.savingstracker.domain.model

data class Expense(
    var title: String = "",
    var value: Int = 0,
    var date: String = "",
    val accountId: Long = 0L,
    val isExpense: Boolean = false
)
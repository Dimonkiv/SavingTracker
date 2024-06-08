package com.dimonkiv.savingstracker.domain.model

data class Transaction(
    var id: Long = -1L,
    var incomeAccount: AccountType = AccountType.bank(),
    var expenseAccount: AccountType = AccountType.cash(),
    var value: String = "",
    var date: String = "",
    var type: TransactionType = TransactionType.INCOME
)

enum class TransactionType {
    INCOME, EXPENSE, TRANSFER
}

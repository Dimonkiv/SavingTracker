package com.dimonkiv.savingstracker.presentation.transaction

data class TransactionState(
    var transactions: List<TransactionTypeState> = emptyList(),
    var value: String = "",
    var valueError: String? = null,
    var typeInt: Int = 0
)

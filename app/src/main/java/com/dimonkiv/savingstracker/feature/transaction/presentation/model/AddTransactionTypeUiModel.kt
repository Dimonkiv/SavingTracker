package com.dimonkiv.savingstracker.feature.transaction.presentation.model

import androidx.compose.runtime.Immutable
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.feature.transaction.domain.TransactionType

@Immutable
data class AddTransactionTypeUiModel(
    val stringRes: Int,
    val type: TransactionType
)

fun TransactionType.asPresentation() = AddTransactionTypeUiModel(
    stringRes = when (this) {
        TransactionType.EXPENSE -> R.string.expense
        TransactionType.INCOME -> R.string.income
        TransactionType.TRANSFER -> R.string.transfer
    },
    type = this
)

fun List<TransactionType>.asPresentation() = map { it.asPresentation() }
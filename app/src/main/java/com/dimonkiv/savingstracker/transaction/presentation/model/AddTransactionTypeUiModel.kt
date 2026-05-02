package com.dimonkiv.savingstracker.transaction.presentation.model

import androidx.compose.runtime.Immutable
import com.dimonkiv.savingstracker.R

@Immutable
data class AddTransactionTypeUiModel(
    val stringRes: Int,
    val type: AddTransactionType
)

fun AddTransactionType.asPresentation() = AddTransactionTypeUiModel(
    stringRes = when (this) {
        AddTransactionType.EXPENSE -> R.string.expense
        AddTransactionType.INCOME -> R.string.income
        AddTransactionType.TRANSFER -> R.string.transfer
    },
    type = this
)

fun List<AddTransactionType>.asPresentation() = map { it.asPresentation() }
package com.dimonkiv.savingstracker.feature.transaction.presentation.model

import androidx.compose.runtime.Immutable
import com.dimonkiv.savingstracker.core.designsystem.R
import com.dimonkiv.savingstracker.core.utils.DateUtils
import com.dimonkiv.savingstracker.core.utils.ResourceManager
import com.dimonkiv.savingstracker.core.mvi.model.UiState
import com.dimonkiv.savingstracker.feature.transaction.domain.AddTransactionModel
import com.dimonkiv.savingstracker.feature.transaction.domain.TransactionType

@Immutable
data class AddTransactionUiModel(
    val title: String = "",
    val balance: String = "",
    val note: String = "",
    val date: String = "",
    val timestamp: Long = 0,
    val selectedType: TransactionType = TransactionType.EXPENSE,
    val types: List<AddTransactionTypeUiModel> = emptyList(),
    val accounts: List<TransactionAccountModel> = emptyList(),
    val selectedAccount: TransactionAccountModel? = null
) : UiState

fun AddTransactionModel.asPresentation(resourceManager: ResourceManager) = AddTransactionUiModel(
    title = this.title,
    balance = this.balance,
    note = this.note,
    timestamp = this.timestamp,
    date = if (this.timestamp == -1L)
        resourceManager.getString(R.string.select_date)
    else
        DateUtils.parseDate(this.timestamp),
    selectedType = this.selectedType,
    types = this.types.asPresentation(),
    accounts = accounts.toTransactionModels(),
    selectedAccount = selectedAccount?.toTransactionModel()
)
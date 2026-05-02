package com.dimonkiv.savingstracker.transaction.presentation.model

import androidx.compose.runtime.Immutable
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.account.presentation.accounts.model.AccountModel
import com.dimonkiv.savingstracker.account.presentation.accounts.model.asPresentation
import com.dimonkiv.savingstracker.core.utils.DateUtils
import com.dimonkiv.savingstracker.core.utils.ResourceManager
import com.dimonkiv.savingstracker.shared.model.UiState
import com.dimonkiv.savingstracker.transaction.domain.AddTransactionModel

@Immutable
data class AddTransactionUiModel(
    val title: String = "",
    val balance: String = "",
    val note: String = "",
    val date: String = "",
    val timestamp: Long = 0,
    val selectedType: AddTransactionType = AddTransactionType.EXPENSE,
    val types: List<AddTransactionTypeUiModel> = mutableListOf(),
    val accounts: List<AccountModel> = mutableListOf(),
    val selectedAccount: AccountModel? = null
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
    accounts = accounts.asPresentation(),
    selectedAccount = selectedAccount?.asPresentation()
)
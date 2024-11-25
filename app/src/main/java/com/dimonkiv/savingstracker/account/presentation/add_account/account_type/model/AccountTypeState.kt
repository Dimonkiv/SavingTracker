package com.dimonkiv.savingstracker.account.presentation.add_account.account_type.model

import com.dimonkiv.savingstracker.core.model.UiState

data class AccountTypeState(
    val types: List<AccountTypeModel> = emptyList()
): UiState

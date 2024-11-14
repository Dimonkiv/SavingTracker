package com.dimonkiv.savingstracker.presentation.account.add_account.account_type.model

import com.dimonkiv.savingstracker.presentation.core.model.UiState

data class AccountTypeState(
    val types: List<AccountTypeModel> = emptyList()
): UiState

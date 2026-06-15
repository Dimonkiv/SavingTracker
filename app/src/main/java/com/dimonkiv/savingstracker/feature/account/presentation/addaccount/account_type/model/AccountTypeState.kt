package com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model

import com.dimonkiv.savingstracker.core.mvi.model.UiState

data class AccountTypeState(
    val types: List<AccountTypeModel> = emptyList()
): UiState

package com.dimonkiv.savingstracker.feature.account.presentation.accounts

import com.dimonkiv.savingstracker.core.mvi.Reducer
import kotlinx.collections.immutable.toImmutableList

class AccountsReducer: Reducer<AccountsState, AccountsAction> {
    override fun reduce(
        state: AccountsState,
        action: AccountsAction
    ): AccountsState {
        return when(action) {
            is AccountsAction.Loaded -> state.copy(
                totalBalance = action.totalBalance,
                types = action.types.toImmutableList(),
                isLoading = false
            )

            is AccountsAction.Loading -> state.copy(
                isLoading = true,
                error = null
            )

            is AccountsAction.Error -> state.copy(
                isLoading = false,
                error = action.error
            )
            is AccountsAction.ClearError -> state.copy(error = null)
        }
    }
}
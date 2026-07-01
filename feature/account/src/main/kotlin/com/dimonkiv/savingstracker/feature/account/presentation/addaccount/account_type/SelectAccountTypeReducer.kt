package com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type

import com.dimonkiv.savingstracker.core.mvi.Reducer
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.AccountTypeState

class SelectAccountTypeReducer : Reducer<AccountTypeState, SelectAccountTypeAction> {
    override fun reduce(state: AccountTypeState, action: SelectAccountTypeAction): AccountTypeState {
        return when (action) {
            is SelectAccountTypeAction.TypesLoaded -> state.copy(types = action.types)
            is SelectAccountTypeAction.Error -> state.copy(error = action.message)
        }
    }
}

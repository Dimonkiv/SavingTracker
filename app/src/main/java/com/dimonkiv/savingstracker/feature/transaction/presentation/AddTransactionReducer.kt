package com.dimonkiv.savingstracker.feature.transaction.presentation

import com.dimonkiv.savingstracker.core.mvi.Reducer
import com.dimonkiv.savingstracker.core.utils.DateUtils
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.AddTransactionUiModel

class AddTransactionReducer: Reducer<AddTransactionUiModel, AddTransactionAction> {
    override fun reduce(
        state: AddTransactionUiModel,
        action: AddTransactionAction
    ): AddTransactionUiModel {
        return when(action) {
            is AddTransactionAction.SetInitialData -> action.model
            is AddTransactionAction.SetBalance -> state.copy(balance = action.balance)
            is AddTransactionAction.SetNote -> state.copy(note = action.note)
            is AddTransactionAction.SetDate -> state.copy(
                timestamp = action.timestamp,
                date = DateUtils.parseDate(action.timestamp)
            )
            is AddTransactionAction.SetSelectedAccount -> state.copy(selectedAccount = action.account)
        }
    }
}
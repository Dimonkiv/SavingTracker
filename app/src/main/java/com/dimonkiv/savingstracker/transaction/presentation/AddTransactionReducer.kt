package com.dimonkiv.savingstracker.transaction.presentation

import com.dimonkiv.savingstracker.core.utils.DateUtils
import com.dimonkiv.savingstracker.transaction.presentation.model.AddTransactionUiModel

class AddTransactionReducerImpl: AddTransactionReducer {
    override fun reduce(
        state: AddTransactionUiModel,
        event: Event
    ): AddTransactionUiModel {
        return when(event) {
            is Event.OnBalanceTextChanged -> state.copy(balance = event.balance)
            is Event.OnNoteTextChanged -> state.copy(note = event.note)
            is Event.OnDateChanged -> updateDate(event.timestamp, state)
            else -> state
        }
    }

    private fun updateDate(
        timestamp: Long,
        state: AddTransactionUiModel
    ) = state.copy(
        timestamp = timestamp,
        date = DateUtils.parseDate(timestamp)
    )
}

interface AddTransactionReducer {
    fun reduce(state: AddTransactionUiModel, event: Event): AddTransactionUiModel
}
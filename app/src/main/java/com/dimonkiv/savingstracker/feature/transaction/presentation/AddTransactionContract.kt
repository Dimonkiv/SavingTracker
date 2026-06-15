package com.dimonkiv.savingstracker.feature.transaction.presentation

import com.dimonkiv.savingstracker.core.mvi.model.UiEffect
import com.dimonkiv.savingstracker.core.mvi.model.UiEvent

sealed class Event : UiEvent {
    data object LoadData: Event()
    data class OnBalanceTextChanged(val balance: String): Event()
    data class OnNoteTextChanged(val note: String): Event()
    data class OnDateChanged(val timestamp: Long): Event()
}

sealed class Effect : UiEffect
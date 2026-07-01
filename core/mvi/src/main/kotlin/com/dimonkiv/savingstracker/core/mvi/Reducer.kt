package com.dimonkiv.savingstracker.core.mvi

import com.dimonkiv.savingstracker.core.mvi.model.UiAction
import com.dimonkiv.savingstracker.core.mvi.model.UiState

interface Reducer<S: UiState, A: UiAction> {
    fun reduce(state: S, action: A): S
}
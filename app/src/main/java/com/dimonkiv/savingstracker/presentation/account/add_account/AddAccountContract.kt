package com.dimonkiv.savingstracker.presentation.account.add_account

import com.dimonkiv.savingstracker.presentation.account.add_account.model.AddAccountModel
import com.dimonkiv.savingstracker.presentation.core.model.UiEffect
import com.dimonkiv.savingstracker.presentation.core.model.UiEvent
import com.dimonkiv.savingstracker.presentation.core.model.UiState

class AddAccountContract {
    sealed class Event: UiEvent {
        data class OnDataReceived(val colorName: String?, val iconRes: Int?): Event()
    }

    data class State(
        val model: AddAccountModel
    ): UiState

    sealed class Effect: UiEffect
}
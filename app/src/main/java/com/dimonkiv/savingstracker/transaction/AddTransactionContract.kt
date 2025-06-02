package com.dimonkiv.savingstracker.transaction

import com.dimonkiv.savingstracker.shared.model.UiEffect
import com.dimonkiv.savingstracker.shared.model.UiEvent
import com.dimonkiv.savingstracker.shared.model.UiState

sealed class Event : UiEvent

sealed class Effect : UiEffect

sealed class AccountState : UiState
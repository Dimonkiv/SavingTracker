package com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type

import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.core.mvi.BaseViewModel
import com.dimonkiv.savingstracker.core.mvi.model.UiEffect
import com.dimonkiv.savingstracker.core.mvi.model.UiEvent
import com.dimonkiv.savingstracker.feature.account.domain.use_cases.GetAccountTypesUseCase
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.AccountTypeState
import kotlinx.coroutines.launch

class SelectAccountTypeViewModel(
    private val useCase: GetAccountTypesUseCase
): BaseViewModel<UiEvent, AccountTypeState, UiEffect>() {

    init {
        fetchTypes()
    }

    override fun createInitialState(): AccountTypeState = AccountTypeState()

    override fun handleEvent(event: UiEvent) = Unit

    private fun fetchTypes() {
        viewModelScope.launch {
            runCatching {
                useCase.invoke()
            }.onSuccess { items ->
                setState { currentState.copy(types = items) }
            }
        }
    }
}
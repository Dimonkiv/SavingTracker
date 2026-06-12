package com.dimonkiv.savingstracker.account.presentation.addaccount.account_type

import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.account.domain.use_cases.GetAccountTypesUseCase
import com.dimonkiv.savingstracker.account.presentation.addaccount.account_type.model.AccountTypeState
import com.dimonkiv.savingstracker.shared.BaseViewModel
import com.dimonkiv.savingstracker.shared.model.UiEffect
import com.dimonkiv.savingstracker.shared.model.UiEvent
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
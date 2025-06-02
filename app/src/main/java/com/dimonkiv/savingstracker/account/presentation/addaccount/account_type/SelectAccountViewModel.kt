package com.dimonkiv.savingstracker.account.presentation.addaccount.account_type

import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.account.domain.repository.AccountTypeRepository
import com.dimonkiv.savingstracker.account.presentation.addaccount.account_type.model.AccountTypeState
import com.dimonkiv.savingstracker.shared.BaseViewModel
import com.dimonkiv.savingstracker.shared.model.UiEffect
import com.dimonkiv.savingstracker.shared.model.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SelectAccountViewModel @Inject constructor(
    private val repository: AccountTypeRepository
): BaseViewModel<UiEvent, AccountTypeState, UiEffect>() {

    init {
        fetchTypes()
    }

    override fun createInitialState(): AccountTypeState = AccountTypeState()

    override fun handleEvent(event: UiEvent) = Unit

    private fun fetchTypes() {
        viewModelScope.launch {
            runCatching {
                repository.fetchAccountTypes()
            }.onSuccess { items ->
                setState { currentState.copy(types = items) }
            }
        }
    }
}
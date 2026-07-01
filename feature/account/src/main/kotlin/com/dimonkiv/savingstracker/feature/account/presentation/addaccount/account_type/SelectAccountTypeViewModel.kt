package com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type

import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.core.coroutine.runCoroutineCatching
import com.dimonkiv.savingstracker.core.mvi.BaseViewModel
import com.dimonkiv.savingstracker.feature.account.domain.usecase.GetAccountTypesUseCase
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.AccountTypeState
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.asPresentation
import kotlinx.coroutines.launch

class SelectAccountTypeViewModel(
    private val useCase: GetAccountTypesUseCase,
    reducer: SelectAccountTypeReducer
) : BaseViewModel<SelectAccountTypeIntent, AccountTypeState, SelectAccountTypeEffect, SelectAccountTypeAction>(
    initialState = AccountTypeState(),
    reducer = reducer
) {

    init {
        fetchTypes()
    }

    override fun handleIntent(intent: SelectAccountTypeIntent) {
        // no intents yet
    }

    private fun fetchTypes() {
        viewModelScope.launch {
            runCoroutineCatching {
                useCase.invoke()
            }.onSuccess { items ->
                reduce(SelectAccountTypeAction.TypesLoaded(items.asPresentation()))
            }.onFailure {
                reduce(SelectAccountTypeAction.Error("Failed to load account types"))
            }
        }
    }
}

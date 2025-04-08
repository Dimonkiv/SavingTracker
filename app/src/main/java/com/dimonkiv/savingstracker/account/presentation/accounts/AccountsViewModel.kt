package com.dimonkiv.savingstracker.account.presentation.accounts

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.account.domain.use_cases.GetAccountsUseCase
import com.dimonkiv.savingstracker.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val useCase: GetAccountsUseCase
) : BaseViewModel<Event, AccountState, Effect>() {

    init {
        setEvent(Event.LoadAccounts)
    }

    override fun createInitialState(): AccountState = AccountState.Idle

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.LoadAccounts -> {
                fetchAccounts()
            }

            is Event.OnErrorDialogClick -> {
                setState { AccountState.Idle }
            }
        }
    }

    private fun fetchAccounts() {
        setState { AccountState.Loading }
        viewModelScope.launch {
            runCatching {
                useCase.invoke()
            }.onSuccess { account ->
                if (account.types.isEmpty()) {
                    setState { AccountState.Idle }
                } else {
                    setState { AccountState.Success(account) }
                }
            }.onFailure { ex ->
                Log.d("TEST", ex.toString())
                setState { AccountState.Error(ex.toString()) }
            }
        }
    }
}
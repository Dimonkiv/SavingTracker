package com.dimonkiv.savingstracker.presentation.account.accounts

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.domain.use_cases.GetAccountUseCase
import com.dimonkiv.savingstracker.presentation.account.accounts.AccountContract.*
import com.dimonkiv.savingstracker.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val useCase: GetAccountUseCase
) : BaseViewModel<Event, State, Effect>() {

    init {
        setEvent(Event.LoadAccounts)
    }

    override fun createInitialState(): State = State(
        AccountState.Idle
    )

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.LoadAccounts -> {
                fetchAccounts()
            }

            is Event.OnErrorDialogClick -> {
                setState { State(AccountState.Idle) }
            }
        }
    }

    private fun fetchAccounts() {
        setState { State(AccountState.Loading) }
        viewModelScope.launch {
            runCatching {
                useCase.invoke()
            }.onSuccess { account ->
                if (account.accounts.isEmpty()) {
                    setState { State(AccountState.Idle) }
                } else {
                    setState { State(AccountState.Success(account)) }
                }
            }.onFailure { ex ->
                Log.d("TEST", ex.toString())
                setState { State(AccountState.Error(ex.toString())) }
            }
        }
    }
}
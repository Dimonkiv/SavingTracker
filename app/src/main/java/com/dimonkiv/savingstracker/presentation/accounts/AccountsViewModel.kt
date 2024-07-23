package com.dimonkiv.savingstracker.presentation.accounts

import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.domain.use_cases.GetAccountUseCase
import com.dimonkiv.savingstracker.presentation.core.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val useCase: GetAccountUseCase
) : BaseViewModel<AccountContract.Event, AccountContract.State, AccountContract.Effect>() {

    init {
        setEvent(AccountContract.Event.LoadAccounts)
    }

    override fun createInitialState(): AccountContract.State = AccountContract.State(
        AccountContract.AccountState.Idle
    )

    override fun handleEvent(event: AccountContract.Event) {
        when (event) {
            is AccountContract.Event.LoadAccounts -> {
                fetchAccounts()
            }
        }
    }

    private fun fetchAccounts() {
        setState { AccountContract.State(AccountContract.AccountState.Loading) }
        viewModelScope.launch {
            val account = useCase.invoke()
            setState { AccountContract.State(AccountContract.AccountState.Success(account)) }
        }
    }


}
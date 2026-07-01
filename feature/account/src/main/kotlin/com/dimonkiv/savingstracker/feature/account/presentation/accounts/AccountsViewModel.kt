package com.dimonkiv.savingstracker.feature.account.presentation.accounts

import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.core.mvi.BaseViewModel
import com.dimonkiv.savingstracker.feature.account.domain.usecase.GetAccountsUseCase
import com.dimonkiv.savingstracker.feature.account.presentation.accounts.model.asUi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlin.coroutines.cancellation.CancellationException

class AccountsViewModel(
    private val getAccounts: GetAccountsUseCase,
    reducer: AccountsReducer
) : BaseViewModel<AccountsIntent, AccountsState, AccountsEffect, AccountsAction>(
    initialState = AccountsState(),
    reducer = reducer
) {

    init {
        observeAccounts()
    }

    override fun handleIntent(intent: AccountsIntent) {
        when (intent) {
            is AccountsIntent.OnErrorDialogClick -> reduce(AccountsAction.ClearError)
            is AccountsIntent.OnAddClicked -> sendEffect(AccountsEffect.NavigateToAddAccount)
        }
    }

    private fun observeAccounts() {
        getAccounts.invoke()
            .onStart {
                reduce(AccountsAction.Loading)
            }
            .onEach {
                reduce(
                    AccountsAction.Loaded(
                        totalBalance = it.totalBalance,
                        types = it.types.asUi()
                    )
                )
            }.catch { err ->
                if (err is CancellationException) throw err

                reduce(
                    AccountsAction.Error(
                        "There is something wrong!"
                    )
                )
            }
            .launchIn(viewModelScope)
    }
}
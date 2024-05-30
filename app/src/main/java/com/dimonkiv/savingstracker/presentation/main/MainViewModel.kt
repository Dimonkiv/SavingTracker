package com.dimonkiv.savingstracker.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.domain.repository.ExpenseRepository
import com.dimonkiv.savingstracker.presentation.add_expense.AddExpenseFragment
import com.dimonkiv.savingstracker.presentation.main.MainEvent.*
import com.dimonkiv.savingstracker.domain.use_cases.GetAccountsUseCase
import com.dimonkiv.savingstracker.domain.use_cases.GetTotalBalanceUseCase
import com.dimonkiv.savingstracker.domain.use_cases.RemoveAccountUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val expenseRepository: ExpenseRepository,
    private val getTotalBalanceUseCase: GetTotalBalanceUseCase,
    private val removeAccountUseCase: RemoveAccountUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    private val _uiEvent = MutableSharedFlow<MainUiEvent>()

    val state = _state.asStateFlow()
    val uiEvent = _uiEvent.asSharedFlow()


    fun onEvent(event: MainEvent) {
        when (event) {
            is FetchData -> {
                fetchData()
            }

            is RemoveAccount -> {
                removeAccount(event.id)
            }

            is OnPageChange -> {
                onPageChanged(event.id)
            }
        }
    }

    private fun removeAccount(id: Long) {
        viewModelScope.launch {
            val accounts = removeAccountUseCase.invoke(id)
            _state.emit(_state.value.copy(accounts = accounts))
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            val accounts = getAccountsUseCase.invoke()
            val total = getTotalBalanceUseCase.invoke()

            _state.emit(_state.value.copy(accounts = accounts, totalBalance = total))
            onPageChanged(_state.value.openedAccount)
        }
    }

    fun onPageChanged(pos: Int) {
        viewModelScope.launch {
            val accountId = _state.value.accounts.getOrNull(pos)?.id ?: 0
            val items = expenseRepository.getExpenseByAccountId(accountId)

            _state.emit(_state.value.copy(expenses = items, openedAccount = pos))
        }
    }

    private fun sendUiEvent(event: MainUiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
}
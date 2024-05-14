package com.dimonkiv.savingstracker.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.domain.ExpenseRepository
import com.dimonkiv.savingstracker.presentation.add_expense.AddExpenseFragment
import com.dimonkiv.savingstracker.presentation.main.MainEvent.*
import com.dimonkiv.savingstracker.use_case.GetAccountsUseCase
import com.dimonkiv.savingstracker.use_case.GetTotalBalanceUseCase
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
    private val getTotalBalanceUseCase: GetTotalBalanceUseCase
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

            is OnPageChange -> {
                onPageChanged(event.id)
            }

            is OnScreenHeightChange -> {
                val measuredHeight = event.screenHeight - event.mainViewHeight
                sendUiEvent(MainUiEvent.InitBottomSheet(measuredHeight))
            }

            is OnLayoutChange -> {
                sendUiEvent(MainUiEvent.MeasureHeight)
            }

            is OnAddCardClick -> {
                sendUiEvent(
                    MainUiEvent.Navigate(
                        direction = R.id.action_mainFragment_to_addAccountFragment,
                        arg = null
                    )
                )
            }

            is OnAddExpenseClick -> {
                sendUiEvent(
                    MainUiEvent.Navigate(
                        direction = R.id.action_mainFragment_to_addExpenseFragment,
                        arg = AddExpenseFragment.createArg(event.id)
                    )
                )

            }
        }
    }

    private fun fetchData() {
        val accounts = getAccountsUseCase.execute()
        val total = getTotalBalanceUseCase.execute()

        updateState(_state.value.copy(accounts = accounts, totalBalance = total))
        onPageChanged(0)
    }

    fun onPageChanged(pos: Int) {
        val accountId = _state.value.accounts.getOrNull(pos)?.id ?: 0
        val items = expenseRepository.fetchExpensesById(accountId)

        updateState(_state.value.copy(expenses = items))
    }

    private fun updateState(state: MainScreenState) {
        viewModelScope.launch {
            _state.emit(state)
        }
    }

    private fun sendUiEvent(event: MainUiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
        }
    }
}
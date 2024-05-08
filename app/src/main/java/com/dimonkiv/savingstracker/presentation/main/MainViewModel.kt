package com.dimonkiv.savingstracker.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.domain.ExpenseRepository
import com.dimonkiv.savingstracker.domain.model.Account
import com.dimonkiv.savingstracker.domain.model.Expense
import com.dimonkiv.savingstracker.use_case.GetAccountsUseCase
import com.dimonkiv.savingstracker.use_case.GetTotalBalanceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAccountsUseCase: GetAccountsUseCase,
    private val expenseRepository: ExpenseRepository,
    private val getTotalBalanceUseCase: GetTotalBalanceUseCase
): ViewModel() {

    private val _accounts: MutableStateFlow<List<Account>> = MutableStateFlow(emptyList())
    private val _totalBalance: MutableStateFlow<String> = MutableStateFlow("")
    private val _expenses: MutableStateFlow<List<Expense>> = MutableStateFlow(emptyList())
    private val _bottomSheetHeight: MutableStateFlow<Int> = MutableStateFlow(0)
    private val _emptyExpenses: MutableStateFlow<Boolean> = MutableStateFlow(true)
    private val _measureHeightAction = MutableSharedFlow<Boolean>(replay = 0)

    val measureHeightAction = _measureHeightAction.asSharedFlow()
    val accounts: StateFlow<List<Account>> = _accounts
    val totalBalance: StateFlow<String> = _totalBalance
    val expenses: StateFlow<List<Expense>> = _expenses
    val bottomSheetHeight: StateFlow<Int> = _bottomSheetHeight
    val emptyExpenses: StateFlow<Boolean> = _emptyExpenses



    fun fetchData() {
        viewModelScope.launch {
            val items = getAccountsUseCase.execute()
            _totalBalance.emit(getTotalBalanceUseCase.execute())
            _accounts.emit(items)
        }
    }

    fun onPageChanged(pos: Int) {
        viewModelScope.launch {
            val accountId = accounts.value.getOrNull(pos)?.id ?: 0
            val items = expenseRepository.fetchExpensesById(accountId)
            _emptyExpenses.emit(items.isEmpty())
            _expenses.emit(items)
        }
    }

    fun onScreenHeightAvailable(screenHeight: Int, mainViewHeight: Int) {
        viewModelScope.launch {
            val measuredHeight = screenHeight - mainViewHeight

            _bottomSheetHeight.emit(measuredHeight)
        }
    }

    fun onLayoutChanged() {
        viewModelScope.launch {
            _measureHeightAction.emit(true)
        }
    }
}
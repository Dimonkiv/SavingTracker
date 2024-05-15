package com.dimonkiv.savingstracker.presentation.add_expense

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.presentation.utils.NumberUtils
import com.dimonkiv.savingstracker.domain.repository.ExpenseRepository
import com.dimonkiv.savingstracker.domain.model.Expense
import com.dimonkiv.savingstracker.presentation.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(
    private val expenseRepository: ExpenseRepository
): ViewModel() {

    private var accountId = 0L

    private var _state = MutableStateFlow(AddExpenseState())
    private var _event = MutableSharedFlow<AddExpenseUiEvent>()

    val state = _state.asStateFlow()
    val event = _event.asSharedFlow()

    fun updateAccountId(accountId: Long) {
        this.accountId = accountId
    }

    fun onEvent(event: AddExpenseEvent) {
        when(event) {
            is AddExpenseEvent.OnTitleChange -> {
                updateState(_state.value.copy(title = event.title, titleError = null))
            }

            is AddExpenseEvent.OnExpenseChange -> {
                updateState(_state.value.copy(expense = event.expense, expenseError = null))
            }

            is AddExpenseEvent.OnExpenseSelected -> {
                updateState(_state.value.copy(isExpense = true))
            }

            is AddExpenseEvent.OnIncomeSelected -> {
                updateState(_state.value.copy(isExpense = false))
            }

            is AddExpenseEvent.OnCreateClick -> {
                createExpense()
            }
        }
    }

    private fun createExpense() {
        viewModelScope.launch {
            if (state.value.title.isBlank()) {
                _state.emit(_state.value.copy(titleError = "Expense can't be empty"))
            }

            if (state.value.expense.isBlank()) {
                _state.emit(_state.value.copy(expenseError = "Expense can't be empty"))
            }

            if (state.value.title.isNotBlank() && state.value.expense.isNotBlank()) {
                expenseRepository.insertExpense(
                    Expense(
                        title = state.value.title,
                        value = NumberUtils.convertToInt(_state.value.expense),
                        isExpense = _state.value.isExpense,
                        date = DateUtils.getCurrentDate(),
                        accountId = accountId
                    )
                )

                _event.emit(AddExpenseUiEvent.ShowMessage("Successfully added!"))
                _event.emit(AddExpenseUiEvent.PopBackStack)
            }
        }
    }


    private fun updateState(state: AddExpenseState) {
        viewModelScope.launch {
            _state.emit(state)
        }
    }
}
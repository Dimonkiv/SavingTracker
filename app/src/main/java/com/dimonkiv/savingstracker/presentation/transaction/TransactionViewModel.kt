package com.dimonkiv.savingstracker.presentation.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.presentation.utils.NumberUtils
import com.dimonkiv.savingstracker.domain.model.Expense
import com.dimonkiv.savingstracker.domain.use_cases.AddExpenseUseCase
import com.dimonkiv.savingstracker.domain.use_cases.transaction.GetTransactionUseCase
import com.dimonkiv.savingstracker.presentation.utils.DateUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val getTransactionUseCase: GetTransactionUseCase
): ViewModel() {

    private var accountId = 0L

    private var _state = MutableStateFlow(TransactionState())
    private var _event = MutableSharedFlow<TransactionUiEvent>()

    val state = _state.asStateFlow()
    val event = _event.asSharedFlow()

    fun updateAccountId(accountId: Long) {
        this.accountId = accountId
    }

    fun onEvent(event: TransactionEvent) {
        when(event) {
            is TransactionEvent.LoadData -> {
                val item = getTransactionUseCase.invoke()
                updateState(_state.value.copy(transactions = item.transactions))
            }

            is TransactionEvent.OnCreateClick -> {
                createExpense()
            }

            else -> {}
        }
    }

    private fun createExpense() {
        viewModelScope.launch {
          /*  if (state.value.title.isBlank()) {
                _state.emit(_state.value.copy(titleError = "Expense can't be empty"))
            }

            if (state.value.expense.isBlank()) {
                _state.emit(_state.value.copy(expenseError = "Expense can't be empty"))
            }

            if (state.value.title.isNotBlank() && state.value.expense.isNotBlank()) {*/
                /*addExpenseUseCase.invoke(
                    Expense(
                        title = state.value.title,
                        value = NumberUtils.convertToInt(_state.value.expense),
                        isExpense = _state.value.isExpense,
                        date = DateUtils.getCurrentDate(),
                        accountId = accountId
                    )
                )*/

                _event.emit(TransactionUiEvent.ShowMessage("Successfully added!"))
                _event.emit(TransactionUiEvent.PopBackStack)
            //}
        }
    }


    private fun updateState(state: TransactionState) {
        viewModelScope.launch {
            _state.emit(state)
        }
    }
}
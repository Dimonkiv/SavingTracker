package com.dimonkiv.savingstracker.feature.transaction.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.core.coroutine.runCoroutineCatching
import com.dimonkiv.savingstracker.core.utils.ResourceManager
import com.dimonkiv.savingstracker.core.mvi.BaseViewModel
import com.dimonkiv.savingstracker.core.utils.DateUtils
import com.dimonkiv.savingstracker.feature.transaction.domain.GetAddTransactionUseCase
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.AddTransactionUiModel
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.asPresentation
import kotlinx.coroutines.launch
class AddTransactionViewModel(
    private val getAddTransactionUseCase: GetAddTransactionUseCase,
    private val resourceManager: ResourceManager
) : BaseViewModel<Event, AddTransactionUiModel, Effect>() {

    override fun createInitialState() = AddTransactionUiModel()

    override fun handleEvent(event: Event) {
        when (event) {
            Event.LoadData -> {
                fetchAddTransactionModel()
            }

            is Event.OnBalanceTextChanged -> setState { currentState.copy(balance = event.balance) }
            is Event.OnNoteTextChanged -> setState { currentState.copy(note = event.note) }
            is Event.OnDateChanged -> setState {
                currentState.copy(
                    timestamp = event.timestamp,
                    date = DateUtils.parseDate(event.timestamp)
                )
            }
        }
    }

    private fun fetchAddTransactionModel() {
        viewModelScope.launch {
            runCoroutineCatching {
                getAddTransactionUseCase.invoke()
            }.onSuccess {
                val model = it.asPresentation(resourceManager)
                setState { model }
            }.onFailure {
                Log.e("TAG", "Fail to fetch AddTransactionModel - $it")
            }
        }
    }
}
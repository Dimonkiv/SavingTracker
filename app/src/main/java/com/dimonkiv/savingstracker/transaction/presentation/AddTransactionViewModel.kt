package com.dimonkiv.savingstracker.transaction.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.core.coroutine.runCoroutineCatching
import com.dimonkiv.savingstracker.core.utils.ResourceManager
import com.dimonkiv.savingstracker.shared.BaseViewModel
import com.dimonkiv.savingstracker.transaction.domain.GetAddTransactionUseCase
import com.dimonkiv.savingstracker.transaction.presentation.model.AddTransactionUiModel
import com.dimonkiv.savingstracker.transaction.presentation.model.asPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTransactionViewModel @Inject constructor(
    private val reducer: AddTransactionReducer,
    private val getAddTransactionUseCase: GetAddTransactionUseCase,
    private val resourceManager: ResourceManager
) : BaseViewModel<Event, AddTransactionUiModel, Effect>() {

    override fun createInitialState() = AddTransactionUiModel()

    override fun handleEvent(event: Event) {
        when (event) {
            Event.LoadData -> {
                fetchAddTransactionModel()
            }

            else -> {
                val newState = reducer.reduce(currentState, event)
                setState { newState }
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
package com.dimonkiv.savingstracker.feature.transaction.presentation

import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.core.coroutine.runCoroutineCatching
import com.dimonkiv.savingstracker.core.utils.ResourceManager
import com.dimonkiv.savingstracker.core.mvi.BaseViewModel
import com.dimonkiv.savingstracker.feature.transaction.domain.GetAddTransactionUseCase
import com.dimonkiv.savingstracker.feature.transaction.presentation.AddTransactionAction.SetBalance
import com.dimonkiv.savingstracker.feature.transaction.presentation.AddTransactionAction.SetDate
import com.dimonkiv.savingstracker.feature.transaction.presentation.AddTransactionAction.SetInitialData
import com.dimonkiv.savingstracker.feature.transaction.presentation.AddTransactionAction.SetNote
import com.dimonkiv.savingstracker.feature.transaction.presentation.AddTransactionAction.SetSelectedAccount
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.AddTransactionUiModel
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.asPresentation
import kotlinx.coroutines.launch

class AddTransactionViewModel(
    reducer: AddTransactionReducer,
    private val getAddTransactionUseCase: GetAddTransactionUseCase,
    private val resourceManager: ResourceManager
) : BaseViewModel<AddTransactionIntent, AddTransactionUiModel, AddTransactionEffect, AddTransactionAction>(
    initialState = AddTransactionUiModel(),
    reducer = reducer
) {

    init {
        fetchAddTransactionModel()
    }

    override fun handleIntent(intent: AddTransactionIntent) {
        when (intent) {
            is AddTransactionIntent.OnBalanceTextChanged -> reduce(SetBalance(intent.balance))
            is AddTransactionIntent.OnNoteTextChanged -> reduce(SetNote(intent.note))
            is AddTransactionIntent.OnDateChanged -> reduce(SetDate(intent.timestamp))
            is AddTransactionIntent.OnAccountSelected -> reduce(SetSelectedAccount(intent.account))
            is AddTransactionIntent.OnSaveClicked -> saveTransaction()
            is AddTransactionIntent.OnBackClicked -> sendEffect(AddTransactionEffect.NavigateBack)
        }
    }

    private fun saveTransaction() {
        // TODO: wire to SaveTransactionUseCase once transaction data layer is implemented
        sendEffect(AddTransactionEffect.NavigateBack)
    }

    private fun fetchAddTransactionModel() {
        viewModelScope.launch {
            runCoroutineCatching {
                getAddTransactionUseCase.invoke()
            }.onSuccess { model ->
                reduce(SetInitialData(model.asPresentation(resourceManager)))
            }.onFailure {
                sendEffect(AddTransactionEffect.ShowError("Failed to load transaction data"))
            }
        }
    }
}

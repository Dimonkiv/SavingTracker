package com.dimonkiv.savingstracker.feature.transaction.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.core.coroutine.runCoroutineCatching
import com.dimonkiv.savingstracker.feature.transaction.domain.GetAddTransactionUseCase
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.TransactionAccountModel
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.toTransactionModels
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SelectTransactionAccountViewModel(
    private val getAddTransactionUseCase: GetAddTransactionUseCase
) : ViewModel() {

    private val _accounts = MutableStateFlow<List<TransactionAccountModel>>(emptyList())
    val accounts = _accounts.asStateFlow()

    init {
        viewModelScope.launch {
            runCoroutineCatching {
                getAddTransactionUseCase.invoke()
            }.onSuccess { model ->
                _accounts.value = model.accounts.toTransactionModels()
            }
        }
    }
}

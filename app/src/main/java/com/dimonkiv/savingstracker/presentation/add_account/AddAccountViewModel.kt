package com.dimonkiv.savingstracker.presentation.add_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.core.utils.NumberUtils
import com.dimonkiv.savingstracker.domain.AccountRepository
import com.dimonkiv.savingstracker.domain.model.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository
) : ViewModel() {

    private var _state = MutableStateFlow(AddAccountFormState())
    private var _event = MutableSharedFlow<AddAccountUiEvent>()

    val state = _state.asStateFlow()
    val event = _event.asSharedFlow()

    fun onEvent(event: AddAccountEvent) {
        when (event) {
            is AddAccountEvent.OnTitleChange -> {
                updateState(_state.value.copy(title = event.title, titleError = null))
            }

            is AddAccountEvent.OnBalanceChange -> {
                updateState(_state.value.copy(balance = event.balance))
            }

            is AddAccountEvent.OnBackButtonClick -> {
               sendUiEvent(AddAccountUiEvent.PopBackStack)
            }

            is AddAccountEvent.OnCreateClick -> {
                createAccount(_state.value.title)
            }
        }
    }

    private fun createAccount(title: String) {
        if (title.isNotBlank()) {
            accountRepository.addAccount(
                Account(
                    id = Random.nextLong(),
                    name = _state.value.title,
                    balance = NumberUtils.convertToInt(_state.value.balance)
                )
            )
            sendUiEvent(AddAccountUiEvent.ShowMessage("Successfully added!"))
            sendUiEvent(AddAccountUiEvent.PopBackStack)
        } else {
            updateState(_state.value.copy(titleError = "Title is empty"))
        }
    }

    private fun updateState(state: AddAccountFormState) {
        viewModelScope.launch {
            _state.emit(state)
        }
    }

    private fun sendUiEvent(event: AddAccountUiEvent) {
        viewModelScope.launch {
            _event.emit(event)
        }
    }
}
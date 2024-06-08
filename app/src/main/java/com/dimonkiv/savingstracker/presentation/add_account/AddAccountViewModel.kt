package com.dimonkiv.savingstracker.presentation.add_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.presentation.utils.NumberUtils
import com.dimonkiv.savingstracker.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.domain.model.Account
import com.dimonkiv.savingstracker.domain.model.AccountType
import com.dimonkiv.savingstracker.domain.use_cases.GetAccountTypeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val getAccountTypeUseCase: GetAccountTypeUseCase
) : ViewModel() {

    private var accountId = 0L

    private var _state = MutableStateFlow(AddAccountFormState())
    private var _event = MutableSharedFlow<AddAccountUiEvent>()

    val state = _state.asStateFlow()
    val event = _event.asSharedFlow()

    fun updateAccountId(accountId: Long) {
        this.accountId = accountId
    }

    fun onEvent(event: AddAccountEvent) {
        when (event) {
            is AddAccountEvent.LoadAccount -> {
                fetchAccount()
            }

            is AddAccountEvent.OnTitleChange -> {
                updateState(_state.value.copy(title = event.title, titleError = null))
            }

            is AddAccountEvent.OnBalanceChange -> {
                updateState(_state.value.copy(balance = event.balance))
            }

            is AddAccountEvent.OnBankButtonClick -> {
                updateAccountType(AccountType.Type.BANK)
            }

            is AddAccountEvent.OnCashButtonClick -> {
                updateAccountType(AccountType.Type.CASH)
            }

            is AddAccountEvent.OnInvestButtonClick -> {
                updateAccountType(AccountType.Type.INVEST)
            }

            is AddAccountEvent.OnBackButtonClick -> {
               sendUiEvent(AddAccountUiEvent.PopBackStack)
            }

            is AddAccountEvent.OnCreateClick -> {
                createAccount(_state.value.title)
            }
        }
    }

    private fun updateAccountType(type: AccountType.Type) {
        val accountType = getAccountTypeUseCase.invoke(type)
        updateState(_state.value.copy(type = accountType))
    }

    private fun createAccount(title: String) {
        viewModelScope.launch {
            if (title.isNotBlank()) {
                accountRepository.addAccount(
                    Account(
                        id = accountId,
                        name = _state.value.title,
                        balance = NumberUtils.convertToInt(_state.value.balance),
                        type = _state.value.type
                    )
                )
                _event.emit(AddAccountUiEvent.ShowMessage("Successfully added!"))
                _event.emit(AddAccountUiEvent.PopBackStack)
            } else {
                _state.emit(_state.value.copy(titleError = "Title is empty"))
            }
        }
    }

    private fun fetchAccount() {
        viewModelScope.launch {
            if (accountId != 0L) {
                val account = accountRepository.fetchAccountById(accountId)
                _state.emit(_state.value.copy(
                    title = account.name,
                    balance = account.balance.toString(),
                    type = account.type
                ))

                updateAccountType(_state.value.type.type)
            }
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
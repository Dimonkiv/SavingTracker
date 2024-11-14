package com.dimonkiv.savingstracker.presentation.account.add_account

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.presentation.account.add_account.AddAccountContract.*
import com.dimonkiv.savingstracker.presentation.account.add_account.account_type.model.AccountTypeModel
import com.dimonkiv.savingstracker.presentation.account.add_account.model.AddAccountModel
import com.dimonkiv.savingstracker.presentation.account.add_account.model.asDomain
import com.dimonkiv.savingstracker.presentation.account.add_account.model.isNotEmpty
import com.dimonkiv.savingstracker.presentation.core.BaseViewModel
import com.dimonkiv.savingstracker.presentation.core.design_system.Dark
import com.dimonkiv.savingstracker.presentation.select_icon.model.ColorMap
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AddAccountViewModel @Inject constructor(
    private val repository: AccountRepository
) : BaseViewModel<Event, AddAccountModel, Effect>() {

    override fun createInitialState(): AddAccountModel {
        return AddAccountModel(
            color = Dark,
            iconRes = -1,
            type = AccountTypeModel(0, "", Dark),
            title = "",
            balance = "",
            showBottomSheet = false,
            isButtonEnabled = false
        )
    }

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.OnBackButtonClicked -> {
                setEffect(Effect.OpenPreviousScreen)
            }

            is Event.OnSelectIconClicked -> {
                setEffect(Effect.OpenSelectIconScreen)
            }

            is Event.OnDismissButtonClick -> {
                setEffect(Effect.HideSelectTypeSheet)
            }

            is Event.OnTypeClicked -> {
                setEffect(Effect.ShowSelectTypeSheet)
            }

            is Event.OnCreateButtonClicked -> {
                createAccount()
            }

            is Event.OnDataReceived -> {
                setState {
                    currentState.copy(
                        color = ColorMap.colors[event.colorName] ?: Dark,
                        iconRes = event.iconRes ?: -1,
                        isButtonEnabled = currentState.isNotEmpty
                    )
                }
            }

            is Event.OnTypeSelected -> {
                setEffect(Effect.HideSelectTypeSheet)
                setState {
                    currentState.copy(
                        type = event.type,
                        isButtonEnabled = currentState.isNotEmpty
                    )
                }
            }

            is Event.OnTitleTextChanged -> {
                setState {
                    currentState.copy(
                        title = event.title,
                        isButtonEnabled = currentState.isNotEmpty
                    )
                }
            }

            is Event.OnBalanceTextChanged -> {
                setState {
                    currentState.copy(
                        balance = event.balance,
                        isButtonEnabled = currentState.isNotEmpty
                    )
                }
            }
        }
    }

    private fun createAccount() {
        viewModelScope.launch {
            runCatching {
                repository.createAccount(currentState.asDomain())
            }.onSuccess {
               setEffect(Effect.OpenPreviousScreen)
            }.onFailure {
                Log.e("TAG", "Fail to create account - $it")
            }
        }
    }
}
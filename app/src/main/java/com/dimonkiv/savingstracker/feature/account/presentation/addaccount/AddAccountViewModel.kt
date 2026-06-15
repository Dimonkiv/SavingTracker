package com.dimonkiv.savingstracker.feature.account.presentation.addaccount

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.core.mvi.BaseViewModel
import com.dimonkiv.savingstracker.designsystem.theme.Dark
import com.dimonkiv.savingstracker.feature.account.domain.use_cases.CreateAccountUseCase
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.AccountTypeModel
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.model.AddAccountModel
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.model.asDomain
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.model.isNotEmpty
import com.dimonkiv.savingstracker.feature.select_icon.presentation.model.ColorMap
import kotlinx.coroutines.launch


class AddAccountViewModel(
    private val useCase: CreateAccountUseCase
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
                useCase.invoke(currentState.asDomain())
            }.onSuccess {
               setEffect(Effect.OpenPreviousScreen)
            }.onFailure {
                Log.e("TAG", "Fail to create account - $it")
            }
        }
    }
}
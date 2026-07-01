package com.dimonkiv.savingstracker.feature.account.presentation.addaccount

import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.core.coroutine.runCoroutineCatching
import com.dimonkiv.savingstracker.core.mvi.BaseViewModel
import com.dimonkiv.savingstracker.designsystem.theme.Dark
import com.dimonkiv.savingstracker.feature.account.domain.usecase.CreateAccountUseCase
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.model.AddAccountModel
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.model.asDomain
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.model.isNotEmpty
import com.dimonkiv.savingstracker.designsystem.theme.ColorMap
import kotlinx.coroutines.launch

class AddAccountViewModel(
    reducer: AddAccountReducer,
    private val useCase: CreateAccountUseCase
) : BaseViewModel<Intent, AddAccountModel, Effect, AddAccountAction>(
    initialState = AddAccountModel(),
    reducer = reducer
) {

    override fun reduce(action: AddAccountAction) {
        super.reduce(action)
        updateState { it.copy(isButtonEnabled = currentState.isNotEmpty) }
    }

    override fun handleIntent(intent: Intent) {
        when (intent) {
            is Intent.OnBackButtonClicked -> sendEffect(Effect.OpenPreviousScreen)
            is Intent.OnSelectIconClicked -> sendEffect(Effect.OpenSelectIconScreen)
            is Intent.OnDismissButtonClick -> sendEffect(Effect.HideSelectTypeSheet)
            is Intent.OnTypeClicked -> sendEffect(Effect.ShowSelectTypeSheet)
            is Intent.OnCreateButtonClicked -> createAccount()
            is Intent.OnTypeSelected -> {
                sendEffect(Effect.HideSelectTypeSheet)
                reduce(AddAccountAction.SetType(intent.type))
            }
            is Intent.OnTitleTextChanged -> reduce(AddAccountAction.SetTitle(intent.title))
            is Intent.OnBalanceTextChanged -> reduce(AddAccountAction.SetBalance(intent.balance))
            is Intent.OnIconAndColorResult -> reduce(
                AddAccountAction.SetInitialData(
                    color = ColorMap.colors.getOrDefault(intent.colorName, Dark),
                    iconRes = intent.iconRes ?: -1
                )
            )
        }
    }


    private fun createAccount() {
        viewModelScope.launch {
            runCoroutineCatching {
                useCase.invoke(currentState.asDomain())
            }.onSuccess {
                sendEffect(Effect.OpenPreviousScreen)
            }.onFailure {
                sendEffect(Effect.ShowError("Failed to create account. Please try again."))
            }
        }
    }
}
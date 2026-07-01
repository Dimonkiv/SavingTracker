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
) : BaseViewModel<AddAccountIntent, AddAccountModel, AddAccountEffect, AddAccountAction>(
    initialState = AddAccountModel(),
    reducer = reducer
) {

    override fun reduce(action: AddAccountAction) {
        super.reduce(action)
        updateState { it.copy(isButtonEnabled = currentState.isNotEmpty) }
    }

    override fun handleIntent(intent: AddAccountIntent) {
        when (intent) {
            is AddAccountIntent.OnBackButtonClicked -> sendEffect(AddAccountEffect.OpenPreviousScreen)
            is AddAccountIntent.OnSelectIconClicked -> sendEffect(AddAccountEffect.OpenSelectIconScreen)
            is AddAccountIntent.OnDismissButtonClick -> sendEffect(AddAccountEffect.HideSelectTypeSheet)
            is AddAccountIntent.OnTypeClicked -> sendEffect(AddAccountEffect.ShowSelectTypeSheet)
            is AddAccountIntent.OnCreateButtonClicked -> createAccount()
            is AddAccountIntent.OnTypeSelected -> {
                sendEffect(AddAccountEffect.HideSelectTypeSheet)
                reduce(AddAccountAction.SetType(intent.type))
            }
            is AddAccountIntent.OnTitleTextChanged -> reduce(AddAccountAction.SetTitle(intent.title))
            is AddAccountIntent.OnBalanceTextChanged -> reduce(AddAccountAction.SetBalance(intent.balance))
            is AddAccountIntent.OnIconAndColorResult -> reduce(
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
                sendEffect(AddAccountEffect.OpenPreviousScreen)
            }.onFailure {
                sendEffect(AddAccountEffect.ShowError("Failed to create account. Please try again."))
            }
        }
    }
}
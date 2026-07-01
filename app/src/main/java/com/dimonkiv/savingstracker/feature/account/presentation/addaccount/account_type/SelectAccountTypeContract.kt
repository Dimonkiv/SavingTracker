package com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type

import com.dimonkiv.savingstracker.core.mvi.model.UiAction
import com.dimonkiv.savingstracker.core.mvi.model.UiEffect
import com.dimonkiv.savingstracker.core.mvi.model.UiIntent
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.AccountTypeModel

sealed interface SelectAccountTypeIntent : UiIntent

sealed class SelectAccountTypeEffect : UiEffect

sealed interface SelectAccountTypeAction : UiAction {
    data class TypesLoaded(val types: List<AccountTypeModel>) : SelectAccountTypeAction
    data class Error(val message: String) : SelectAccountTypeAction
}

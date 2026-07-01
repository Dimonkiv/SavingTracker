package com.dimonkiv.savingstracker.feature.account.presentation.addaccount

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.core.mvi.model.UiAction
import com.dimonkiv.savingstracker.core.mvi.model.UiEffect
import com.dimonkiv.savingstracker.core.mvi.model.UiIntent
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.AccountTypeModel

sealed class Intent : UiIntent {
    data class OnTypeSelected(val type: AccountTypeModel) : Intent()
    data class OnTitleTextChanged(val title: String) : Intent()
    data class OnBalanceTextChanged(val balance: String) : Intent()
    data object OnDismissButtonClick : Intent()
    data object OnSelectIconClicked : Intent()
    data object OnBackButtonClicked : Intent()
    data object OnCreateButtonClicked : Intent()
    data object OnTypeClicked : Intent()
    data class OnIconAndColorResult(val colorName: String?, val iconRes: Int?) : Intent()
}

sealed class Effect : UiEffect {
    data object OpenSelectIconScreen : Effect()
    data object OpenPreviousScreen : Effect()
    data object ShowSelectTypeSheet : Effect()
    data object HideSelectTypeSheet : Effect()
    data class ShowError(val message: String) : Effect()
}

sealed interface AddAccountAction: UiAction {
    data class SetInitialData(
        val color: Color,
        val iconRes: Int
    ): AddAccountAction

    data class SetType(val type: AccountTypeModel): AddAccountAction
    data class SetTitle(val title: String): AddAccountAction
    data class SetBalance(val balance: String): AddAccountAction
}

package com.dimonkiv.savingstracker.feature.account.presentation.addaccount

import androidx.compose.ui.graphics.Color
import com.dimonkiv.savingstracker.core.mvi.model.UiAction
import com.dimonkiv.savingstracker.core.mvi.model.UiEffect
import com.dimonkiv.savingstracker.core.mvi.model.UiIntent
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.AccountTypeModel

sealed class AddAccountIntent : UiIntent {
    data class OnTypeSelected(val type: AccountTypeModel) : AddAccountIntent()
    data class OnTitleTextChanged(val title: String) : AddAccountIntent()
    data class OnBalanceTextChanged(val balance: String) : AddAccountIntent()
    data object OnDismissButtonClick : AddAccountIntent()
    data object OnSelectIconClicked : AddAccountIntent()
    data object OnBackButtonClicked : AddAccountIntent()
    data object OnCreateButtonClicked : AddAccountIntent()
    data object OnTypeClicked : AddAccountIntent()
    data class OnIconAndColorResult(val colorName: String?, val iconRes: Int?) : AddAccountIntent()
}

sealed class AddAccountEffect : UiEffect {
    data object OpenSelectIconScreen : AddAccountEffect()
    data object OpenPreviousScreen : AddAccountEffect()
    data object ShowSelectTypeSheet : AddAccountEffect()
    data object HideSelectTypeSheet : AddAccountEffect()
    data class ShowError(val message: String) : AddAccountEffect()
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

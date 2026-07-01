package com.dimonkiv.savingstracker.feature.account.presentation.accounts

import androidx.compose.runtime.Immutable
import com.dimonkiv.savingstracker.core.mvi.model.UiAction
import com.dimonkiv.savingstracker.core.mvi.model.UiEffect
import com.dimonkiv.savingstracker.core.mvi.model.UiIntent
import com.dimonkiv.savingstracker.core.mvi.model.UiState
import com.dimonkiv.savingstracker.core.data.accountapi.model.AccountTypeWithAccounts
import com.dimonkiv.savingstracker.feature.account.presentation.accounts.model.TypesWithAccountsModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

sealed interface AccountsIntent : UiIntent {
    data object OnErrorDialogClick : AccountsIntent
    data object OnAddClicked : AccountsIntent
}

sealed class AccountsEffect : UiEffect {
    data object NavigateToAddAccount : AccountsEffect()
}

@Immutable
data class AccountsState(
    val totalBalance: String = "",
    val types: ImmutableList<TypesWithAccountsModel> = persistentListOf(),
    val isLoading: Boolean = false,
    val error: String? = null
): UiState

sealed interface AccountsAction: UiAction {
    data object Loading: AccountsAction
    data class Loaded(
        val totalBalance: String,
        val types: List<TypesWithAccountsModel>
    ): AccountsAction
    data class Error(val error: String): AccountsAction
    data object ClearError: AccountsAction
}
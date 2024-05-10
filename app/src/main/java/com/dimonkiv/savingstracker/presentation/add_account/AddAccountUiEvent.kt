package com.dimonkiv.savingstracker.presentation.add_account

sealed class AddAccountUiEvent {
    data object PopBackStack: AddAccountUiEvent()

    data class ShowMessage(val message: String): AddAccountUiEvent()

}
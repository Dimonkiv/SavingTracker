package com.dimonkiv.savingstracker.feature.account.presentation.addaccount

import com.dimonkiv.savingstracker.core.mvi.Reducer
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.model.AddAccountModel

class AddAccountReducer: Reducer<AddAccountModel, AddAccountAction> {
    override fun reduce(
        state: AddAccountModel,
        action: AddAccountAction
    ): AddAccountModel {
        return when(action) {
            is AddAccountAction.SetInitialData -> state.copy(
                color = action.color,
                iconRes = action.iconRes
            )

            is AddAccountAction.SetType -> state.copy(
                type = action.type
            )

            is AddAccountAction.SetTitle -> state.copy(
                title = action.title
            )

            is AddAccountAction.SetBalance -> state.copy(
                balance = action.balance
            )
        }
    }
}
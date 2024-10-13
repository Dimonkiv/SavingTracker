package com.dimonkiv.savingstracker.presentation.account.add_account

import com.dimonkiv.savingstracker.presentation.account.add_account.AddAccountContract.*
import com.dimonkiv.savingstracker.presentation.account.add_account.model.AccountTypeModel
import com.dimonkiv.savingstracker.presentation.account.add_account.model.AddAccountModel
import com.dimonkiv.savingstracker.presentation.core.BaseViewModel
import com.dimonkiv.savingstracker.presentation.core.design_system.Blue
import com.dimonkiv.savingstracker.presentation.core.design_system.Dark
import com.dimonkiv.savingstracker.presentation.core.design_system.Green
import com.dimonkiv.savingstracker.presentation.core.design_system.Orange
import com.dimonkiv.savingstracker.presentation.select_icon.model.ColorMap


class AddAccountViewModel : BaseViewModel<Event, State, Effect>() {

    override fun createInitialState(): State {
        return State(
            AddAccountModel(
                color = Dark,
                iconRes = -1,
                AccountTypeModel(Dark, ""),
                types = getTypes(),
                "",
                "0"
            )
        )
    }

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.OnDataReceived -> {
                setState {
                    State(
                        AddAccountModel(
                            color = ColorMap.colors[event.colorName] ?: Dark,
                            iconRes = event.iconRes ?: -1,
                            type = currentState.model.type,
                            types = currentState.model.types,
                            title = currentState.model.title,
                            balance = currentState.model.balance
                        )
                    )
                }
            }

            is Event.OnTypeClicked -> {
                setEffect(Effect.ShowSelectTypeScreen)
            }

            is Event.OnTypeSelect -> {
                setState {
                    State(
                        AddAccountModel(
                            color = currentState.model.color,
                            iconRes = currentState.model.iconRes,
                            type = event.type,
                            types = currentState.model.types,
                            title = currentState.model.title,
                            balance = currentState.model.balance
                        )
                    )
                }
            }
        }
    }

    private fun getTypes(): List<AccountTypeModel> {
        return listOf(
            AccountTypeModel(color = Green, "Cash"),
            AccountTypeModel(color = Blue, "Bank account"),
            AccountTypeModel(color = Orange, "Invest"),
        )
    }
}
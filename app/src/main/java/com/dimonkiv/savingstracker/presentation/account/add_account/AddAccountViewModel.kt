package com.dimonkiv.savingstracker.presentation.account.add_account

import com.dimonkiv.savingstracker.presentation.account.add_account.AddAccountContract.*
import com.dimonkiv.savingstracker.presentation.account.add_account.model.AddAccountModel
import com.dimonkiv.savingstracker.presentation.core.BaseViewModel
import com.dimonkiv.savingstracker.presentation.core.design_system.Dark
import com.dimonkiv.savingstracker.presentation.core.design_system.LightDark
import com.dimonkiv.savingstracker.presentation.select_icon.model.ColorMap
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AddAccountViewModel : BaseViewModel<Event, State, Effect>() {

    override fun createInitialState(): State {
        return State(AddAccountModel(color = Dark, iconRes = -1))
    }

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.OnDataReceived -> {
                setState {
                    State(
                        AddAccountModel(
                            color = ColorMap.colors[event.colorName] ?: Dark,
                            iconRes = event.iconRes ?: -1
                        )
                    )
                }
            }
        }
    }
}
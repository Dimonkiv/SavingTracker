package com.dimonkiv.savingstracker.presentation.main2

import androidx.lifecycle.ViewModel
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.presentation.Screen
import com.dimonkiv.savingstracker.presentation.main2.model.BottomItemModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel2 : ViewModel(), ContainerHost<MainState, MainSideEffect> {

    override val container: Container<MainState, MainSideEffect> = container(
        initialState = MainState(
            items = listOf(
                BottomItemModel(
                    iconRes = R.drawable.ic_home,
                    route = Screen.HOME,
                    selected = true
                ),
                BottomItemModel(
                    iconRes = R.drawable.ic_wallet,
                    route = Screen.ACCOUNTS,
                    selected = false
                ),
                BottomItemModel(
                    iconRes = R.drawable.ic_chart,
                    route = Screen.STATISTICS,
                    selected = false
                ),
                BottomItemModel(
                    iconRes = R.drawable.ic_account,
                    route = Screen.PROFILE,
                    selected = false
                )
            )
        )
    )

}
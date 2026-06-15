package com.dimonkiv.savingstracker.feature.main

import androidx.lifecycle.ViewModel
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.core.navigation.Screen
import com.dimonkiv.savingstracker.feature.main.model.BottomItemModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val initialState = listOf(
        BottomItemModel(
            iconRes = R.drawable.ic_home,
            route = Screen.HOME,
            title = "Home",
            selected = true
        ),
        BottomItemModel(
            iconRes = R.drawable.ic_wallet,
            title = "Accounts",
            route = Screen.ACCOUNTS,
            selected = false
        ),
        BottomItemModel(
            iconRes = R.drawable.ic_chart,
            title = "Analytics",
            route = Screen.STATISTICS,
            selected = false
        ),
        BottomItemModel(
            iconRes = R.drawable.ic_account,
            title = "Settings",
            route = Screen.PROFILE,
            selected = false
        )
    )

    private val _uiState : MutableStateFlow<List<BottomItemModel>> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()
}
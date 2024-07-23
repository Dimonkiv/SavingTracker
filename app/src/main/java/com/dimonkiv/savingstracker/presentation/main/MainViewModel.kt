package com.dimonkiv.savingstracker.presentation.main

import androidx.lifecycle.ViewModel
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.presentation.Screen
import com.dimonkiv.savingstracker.presentation.main.model.BottomItemModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val initialState = listOf(
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

    private val _uiState : MutableStateFlow<List<BottomItemModel>> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()
}
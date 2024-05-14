package com.dimonkiv.savingstracker.presentation.main

import android.os.Bundle

sealed class MainUiEvent {

    data class InitBottomSheet(val height: Int): MainUiEvent()

    data object MeasureHeight: MainUiEvent()

    data class Navigate(val direction: Int, val arg: Bundle?): MainUiEvent()
}
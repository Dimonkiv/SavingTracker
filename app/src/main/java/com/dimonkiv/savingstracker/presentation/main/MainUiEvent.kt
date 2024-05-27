package com.dimonkiv.savingstracker.presentation.main

import android.os.Bundle

sealed class MainUiEvent {

    data class Navigate(val direction: Int, val arg: Bundle?): MainUiEvent()
}
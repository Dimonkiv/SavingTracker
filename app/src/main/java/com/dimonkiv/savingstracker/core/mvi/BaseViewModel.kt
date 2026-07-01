package com.dimonkiv.savingstracker.core.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.core.mvi.model.UiAction
import com.dimonkiv.savingstracker.core.mvi.model.UiEffect
import com.dimonkiv.savingstracker.core.mvi.model.UiIntent
import com.dimonkiv.savingstracker.core.mvi.model.UiState
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<I: UiIntent, S: UiState, E: UiEffect, A: UiAction>(
    initialState: S,
    private val reducer: Reducer<S, A>
): ViewModel() {

    // Get Current State
    val currentState: S
        get() = uiState.value

    private val _uiState: MutableStateFlow<S> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()
    private val _effect = Channel<E>()
    val effect = _effect.receiveAsFlow()


    /**
     * Set new Ui State
     */
    protected fun updateState(reducer: (S) -> S) {
        _uiState.update { reducer(it) }
    }

    protected open fun reduce(action: A) {
        _uiState.update { current ->
            reducer.reduce(current, action)
        }
    }

    /**
     * Set new Effect
     */
    protected fun sendEffect(effect: E) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }

    abstract fun handleIntent(intent: I)
}
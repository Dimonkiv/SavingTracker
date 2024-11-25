package com.dimonkiv.savingstracker.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dimonkiv.savingstracker.core.model.UiEffect
import com.dimonkiv.savingstracker.core.model.UiEvent
import com.dimonkiv.savingstracker.core.model.UiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<Event: UiEvent, State: UiState, Effect: UiEffect>: ViewModel() {
    // Create Initial State of View
    private val initialState : State by lazy { createInitialState() }
    abstract fun createInitialState() : State

    // Get Current State
    val currentState: State
        get() = uiState.value

    private val _uiState : MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event : MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect : MutableSharedFlow<Effect> = MutableSharedFlow()
    val effect = _effect.asSharedFlow()

    init {
        subscribeEvents()
    }

    /**
     * Set new Event
     */
    fun setEvent(event : Event) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }


    /**
     * Set new Ui State
     */
    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    /**
     * Set new Effect
     */
    protected fun setEffect(effect: Effect) {
        val newEffect = effect
        viewModelScope.launch { _effect.emit(newEffect) }
    }

    /**
     * Start listening to Event
     */
    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    /**
     * Handle each event
     */
    abstract fun handleEvent(event : Event)
}
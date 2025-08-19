package com.dimonkiv.savingstracker.core

interface Reducer<State: Any, Event: Any> {
    fun reduce(state: State, event: Event): State
}
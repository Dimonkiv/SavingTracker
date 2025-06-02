package com.dimonkiv.savingstracker.shared

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun <T>ConsumeUiEffects(
    uiEffect: Flow<T>,
    consumer: (T) -> Unit
) {
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(uiEffect) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.CREATED) {
            uiEffect.collect { consumer(it) }
        }
    }
}
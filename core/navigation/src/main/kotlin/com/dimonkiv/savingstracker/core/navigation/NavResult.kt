package com.dimonkiv.savingstracker.core.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

/**
 * Minimal cross-entry result channel, replacing Nav2's `NavHostController.savedStateHandle`
 * result-passing. One instance per result type, registered as a Koin `single` and shared by the
 * producing and consuming `NavEntry`'s composables (both must be able to resolve the same
 * instance via Koin, so register it in a module every relevant feature already depends on).
 *
 * Subclass per concrete result type (e.g. `class IconColorResultBus : NavResult<IconColorResult>()`)
 * rather than registering `NavResult<X>` directly as a Koin `single` - the JVM erases `T`, so two
 * different `single { NavResult<X>() }` / `single { NavResult<Y>() }` definitions would collide
 * under the same erased `NavResult::class` key. A named subclass gives each result type its own
 * concrete class Koin can key on.
 */
abstract class NavResult<T : Any> {
    var value: T? by mutableStateOf(null)
        private set

    fun send(result: T) {
        value = result
    }

    fun consume() {
        value = null
    }
}

package com.dimonkiv.savingstracker.core.navigation

import androidx.navigation3.runtime.NavKey

/**
 * Handles navigation events by updating [NavigationState].
 *
 * Registered as a parameterless Koin `single` so every feature module's `navigation<T> { }` entry
 * can `get<Navigator>()`. [NavigationState] itself must be created from a `@Composable`
 * (it relies on `rememberSerializable`/`rememberNavBackStack` for process-death survival), so
 * [attach] is called exactly once from `MainActivity` before the first `NavDisplay` composition.
 */
class Navigator {

    private var _state: NavigationState? = null

    val state: NavigationState
        get() = checkNotNull(_state) { "Navigator.attach(state) must be called before use" }

    fun attach(state: NavigationState) {
        _state = state
    }

    fun navigate(route: NavKey) {
        val current = state
        if (route in current.backStacks.keys) {
            current.topLevelRoute = route
        } else {
            current.backStacks[current.topLevelRoute]?.add(route)
        }
    }

    fun goBack() {
        val current = state
        val currentStack = current.backStacks[current.topLevelRoute]
            ?: error("Stack for ${current.topLevelRoute} not found")
        val currentRoute = currentStack.last()

        if (currentRoute == current.topLevelRoute) {
            current.topLevelRoute = current.startRoute
        } else {
            currentStack.removeLastOrNull()
        }
    }
}

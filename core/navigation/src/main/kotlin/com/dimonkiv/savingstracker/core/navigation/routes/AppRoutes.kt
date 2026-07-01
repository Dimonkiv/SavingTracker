package com.dimonkiv.savingstracker.core.navigation.routes

import androidx.navigation3.runtime.NavKey
import com.dimonkiv.savingstracker.core.navigation.NavResult
import kotlinx.serialization.Serializable

@Serializable
data object Home : NavKey

@Serializable
data object Accounts : NavKey

@Serializable
data object Statistics : NavKey

@Serializable
data object Profile : NavKey

val TOP_LEVEL_ROUTES: Set<NavKey> = setOf(Home, Accounts, Statistics, Profile)

@Serializable
data object AddAccount : NavKey

@Serializable
data object SelectIcon : NavKey

@Serializable
data object AddTransaction : NavKey

@Serializable
data object SelectAccountType : NavKey

@Serializable
data object SelectTransactionAccount : NavKey

@Serializable
data class SelectTransactionDate(val initialDateMillis: Long?) : NavKey

/** Cross-entry result delivered via [NavResult], replacing Nav2's `savedStateHandle` result-passing. */
data class IconColorResult(val iconRes: Int, val colorName: String)

class IconColorResultBus : NavResult<IconColorResult>()

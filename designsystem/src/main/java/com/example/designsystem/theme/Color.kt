package com.dimonkiv.savingstracker.designsystem.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Dark = Color(0xFF2E3240)
val LightDark = Color(0xFF3C404d)
val Purple = Color(0xFF9A69ED)
val Green = Color(0xFF53D260)
val Yellow = Color(0xFFF1C352)
val Gray = Color(0xFFBDBDBD)
val LightGray = Color(0xFFF9F9F9)

// Colors for icon
val LightPurple = Color(0xFF7f7ce3)
val DarkGreen = Color(0xFF4c9e70)
val LightGreen = Color(0xFF02bbb5)
val Orange = Color(0xFFef7949)
val Brown = Color(0xFFad6360)
val Blue = Color(0xFF38a0f7)
val Pink = Color(0xFFee6782)
val Scorpion = Color(0xFF595959)

@Immutable
class AppColorScheme {
    var isDark = false
        internal set
    var lightPurple: Color = Color(0xFF7f7ce3)
        internal set
    var darkGreen: Color = Color(0xFF4c9e70)
        internal set
    var lightGreen: Color = Color(0xFF02bbb5)
        internal set
    var orange: Color = Color(0xFFef7949)
        internal set
    var brown: Color = Color(0xFFad6360)
        internal set
    var blue: Color = Color(0xFF38a0f7)
        internal set
    var pink: Color = Color(0xFFee6782)
        internal set
    var scorpion: Color = Color(0xFF595959)
        internal set

    var primary: Color = Color(0xFF1E88E5)
        internal set
    var onPrimary: Color = Color(0xFFF5F5F5)
        internal set
    var primaryContainer = Color(0xFFBBDEFB)
        internal set
    var onPrimaryContainer = Color(0xFF0D47A1)
        internal set

    var secondary: Color = Color(0xFF009688)
        internal set
    var onSecondary: Color = Color(0xFF424242)
        internal set
    var secondaryContainer = Color(0xFFB2DFDB)
        internal set
    var onSecondaryContainer = Color(0xFF004D40)
        internal set

    var tertiary = Color(0xFFBA68C8)
        internal set
    var onTertiary = Color(0xFFFFFFFF)
        internal set
    var tertiaryContainer = Color(0xFFE1BEE7)
        internal set
    var onTertiaryContainer = Color(0xFF4A148C)
        internal set

    var error = Color(0xFFD32F2F)
        internal set
    var onError = Color(0xFFFFFFFF)
        internal set
    var errorContainer = Color(0xFFFFCDD2)
        internal set
    var onErrorContainer = Color(0xFFB71C1C)
        internal set

    var background = Color(0xFFF5F5F5)
        internal set
    var onBackground = Color(0xFF212121)
        internal set

    var surface = Color(0xFFFFFFFF)
        internal set
    var onSurface = Color(0xFF212121)
        internal set
    var surfaceVariant = Color(0xFFEEEEEE)
        internal set
    var onSurfaceVariant = Color(0xFF424242)
        internal set

    var outline = Color(0xFFBDBDBD)
        internal set

    var inversePrimary = Color(0xFF90CAF9)
        internal set
    var inverseSurface = Color(0xFF212121)
        internal set
    var inverseOnSurface = Color(0xFFF5F5F5)
        internal set

    var textPrimary: Color = Color(0xFF212121)
        internal set
    var textSecondary: Color = Color(0xFF757575)
        internal set
    var divider: Color = Color(0xFFBDBDBD)
        internal set
}

internal fun lightAppColorScheme() = AppColorScheme()

internal fun darkAppColorScheme() = AppColorScheme().apply {
    isDark = true
    primary = Color(0xFF90CAF9)
    onPrimary = Color(0xFF000000)
    primaryContainer = Color(0xFF0D47A1)
    onPrimaryContainer = Color(0xFFE3F2FD)

    secondary = Color(0xFF80CBC4)
    onSecondary = Color(0xFF000000)
    secondaryContainer = Color(0xFF004D40)
    onSecondaryContainer = Color(0xFFE0F2F1)

    tertiary = Color(0xFFCE93D8)
    onTertiary = Color(0xFF000000)
    tertiaryContainer = Color(0xFF4A148C)
    onTertiaryContainer = Color(0xFFF3E5F5)

    error = Color(0xFFEF9A9A)
    onError = Color(0xFF000000)
    errorContainer = Color(0xFFB71C1C)
    onErrorContainer = Color(0xFFFFEBEE)

    background = Color(0xFF121212)
    onBackground = Color(0xFFE0E0E0)

    surface = Color(0xFF1E1E1E)
    onSurface = Color(0xFFE0E0E0)
    surfaceVariant = Color(0xFF37474F)
    onSurfaceVariant = Color(0xFFCFD8DC)

    outline = Color(0xFF616161)

    inversePrimary = Color(0xFF1E88E5)
    inverseSurface = Color(0xFFE0E0E0)
    inverseOnSurface = Color(0xFF121212)
}

fun darkMaterialColorScheme(colorScheme: AppColorScheme): ColorScheme =
    darkColorScheme(
        primary = colorScheme.primary,
        onPrimary = colorScheme.onPrimary,
        primaryContainer = colorScheme.primary,
        onPrimaryContainer = colorScheme.onPrimaryContainer,
        secondary = colorScheme.secondary,
        onSecondary = colorScheme.onSecondary,
        secondaryContainer = colorScheme.secondaryContainer,
        onSecondaryContainer = colorScheme.onSecondaryContainer,
        tertiary = colorScheme.tertiary,
        onTertiary = colorScheme.onTertiary,
        tertiaryContainer = colorScheme.tertiaryContainer,
        onTertiaryContainer = colorScheme.onTertiaryContainer,
        error = colorScheme.error,
        onError = colorScheme.onError,
        errorContainer = colorScheme.errorContainer,
        onErrorContainer = colorScheme.onErrorContainer,
        background = colorScheme.background,
        onBackground = colorScheme.onBackground,
        surface = colorScheme.surface,
        onSurface = colorScheme.onSurface,
        surfaceVariant = colorScheme.surfaceVariant,
        onSurfaceVariant = colorScheme.onSurfaceVariant
    )

fun lightMaterialColorScheme(colorScheme: AppColorScheme): ColorScheme =
    lightColorScheme(
        primary = colorScheme.primary,
        onPrimary = colorScheme.onPrimary,
        primaryContainer = colorScheme.primary,
        onPrimaryContainer = colorScheme.onPrimaryContainer,
        secondary = colorScheme.secondary,
        onSecondary = colorScheme.onSecondary,
        secondaryContainer = colorScheme.secondaryContainer,
        onSecondaryContainer = colorScheme.onSecondaryContainer,
        tertiary = colorScheme.tertiary,
        onTertiary = colorScheme.onTertiary,
        tertiaryContainer = colorScheme.tertiaryContainer,
        onTertiaryContainer = colorScheme.onTertiaryContainer,
        error = colorScheme.error,
        onError = colorScheme.onError,
        errorContainer = colorScheme.errorContainer,
        onErrorContainer = colorScheme.onErrorContainer,
        background = colorScheme.background,
        onBackground = colorScheme.onBackground,
        surface = colorScheme.surface,
        onSurface = colorScheme.onSurface,
        surfaceVariant = colorScheme.surfaceVariant,
        onSurfaceVariant = colorScheme.onSurfaceVariant
    )

val LocalAppColorScheme = staticCompositionLocalOf { AppColorScheme() }





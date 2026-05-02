package com.dimonkiv.savingstracker.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable


@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val isDarkMode = isSystemInDarkTheme()

    //Color scheme
    val colorScheme = if (isDarkMode) darkAppColorScheme() else lightAppColorScheme()
    val materialColorScheme = if (isDarkMode)
        darkMaterialColorScheme(colorScheme)
    else lightMaterialColorScheme(colorScheme)

    // Typography
    val appTypography = AppTypography()
    val materialTypography = materialTypography(appTypography)


    // Composition locals
    CompositionLocalProvider(
        LocalContentColor provides colorScheme.onSurface,
        LocalAppColorScheme provides colorScheme,
        LocalAppTypography provides appTypography,
    ) {
        MaterialTheme(
            colorScheme = materialColorScheme,
            shapes = materialShapes(),
            typography = materialTypography,
        ) {
            ProvideTextStyle(value = appTypography.body1, content = content)
        }
    }
}

object AppTheme {
    /**
     * Retrieves the current [AppColorScheme] at the call site's position in the hierarchy.
     */
    val appColorScheme: AppColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColorScheme.current

    /**
     * Retrieves the current [AppTypography] at the call site's position in the hierarchy.
     */
    val appTypography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current
}

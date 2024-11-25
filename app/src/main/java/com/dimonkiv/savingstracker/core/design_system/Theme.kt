package com.dimonkiv.savingstracker.core.design_system

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Dark,
    secondary = LightDark,
    tertiary = Purple,
    background = Dark
)

@Composable
fun SavingsTrackingTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = MaterialTheme.typography,
        content = content
    )

}
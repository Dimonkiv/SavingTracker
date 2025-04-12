package com.dimonkiv.savingstracker.designsystem.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
class AppTypography(
    val heading: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 28.sp
    ),
    val subheading: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    val body1Heavy: TextStyle = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    val body1Strong: TextStyle = subheading,
    val body1: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    ),
    val body2Strong: TextStyle = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    val body2: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    val caption: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )
)

internal val LocalAppTypography = staticCompositionLocalOf { AppTypography() }


/**
 * App typography for [MaterialTheme].
 */
fun materialTypography(typography: AppTypography) = Typography(
    titleLarge = typography.heading,
    titleMedium = typography.subheading,
    titleSmall = typography.body2Strong,
    bodyLarge = typography.body1Strong,
    bodyMedium = typography.body1,
    bodySmall = typography.caption,
    labelLarge = typography.body2Strong,
    labelMedium = typography.body2,
    labelSmall = typography.caption
)
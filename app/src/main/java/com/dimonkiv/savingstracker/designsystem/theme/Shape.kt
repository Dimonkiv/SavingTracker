package com.dimonkiv.savingstracker.designsystem.theme

import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes

fun materialShapes() = Shapes(
    extraSmall = ShapeDefaults.ExtraSmall,
    small = ShapeDefaults.Small,
    medium = ShapeDefaults.Medium,
    large = ShapeDefaults.Large,
    // To reduce the dialogs corner radius
    extraLarge = ShapeDefaults.Large,
)
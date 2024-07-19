package com.dimonkiv.savingstracker.presentation.main2.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Fab(
    modifier: Modifier,
    fabSize: Dp,
    fabColor: Color,
    iconRes: Int,
    onItemClick: () -> Unit
) {
    LargeFloatingActionButton(
        modifier = modifier
            .size(fabSize),
        shape = CircleShape,
        containerColor = fabColor,
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(defaultElevation = 0.dp),
        onClick = {
            onItemClick()
        }
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(32.dp)
        )
    }
}
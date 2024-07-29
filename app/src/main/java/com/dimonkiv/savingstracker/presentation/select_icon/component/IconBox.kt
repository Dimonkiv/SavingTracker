package com.dimonkiv.savingstracker.presentation.select_icon.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import com.dimonkiv.savingstracker.presentation.core.design_system.LightGray

@Composable
fun IconBox(
    outlineContainerColor: Color,
    containerColor: Color,
    outlineContainerSize: Dp,
    containerSize: Dp,
    iconSize: Dp,
    iconRes: Int = -1,
    clickable: Boolean = false,
    onIconClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .size(outlineContainerSize)
            .clip(CircleShape)
            .background(outlineContainerColor)
            .clickable(
                enabled = clickable,
                onClick = {
                    onIconClick()
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(containerSize)
                .clip(CircleShape)
                .background(containerColor),
            contentAlignment = Alignment.Center
        ) {
            if (iconRes != -1) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    painter = painterResource(id = iconRes),
                    contentDescription = null,
                    tint = LightGray
                )
            }
        }
    }
}
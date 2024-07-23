package com.dimonkiv.savingstracker.presentation.main.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.dimonkiv.savingstracker.presentation.Screen
import com.dimonkiv.savingstracker.presentation.core.design_system.Gray
import com.dimonkiv.savingstracker.presentation.core.design_system.LightGray
import com.dimonkiv.savingstracker.presentation.main.model.BottomItemModel


@Composable
fun BottomBarItem(
    item: BottomItemModel,
    currentDestination: NavDestination?,
    selectedColor: Color = LightGray,
    unselectedColor: Color = Gray,
    iconSize: Dp = 24.dp,
    onClick: (Screen) -> Unit
) {
    val selected = currentDestination?.hierarchy?.any { it.route == item.route.name } == true

    IconButton(onClick = { onClick(item.route) }) {
        Icon(
            painter = painterResource(id = item.iconRes),
            contentDescription = null,
            tint = if (selected) selectedColor else unselectedColor,
            modifier = Modifier.size(iconSize)
        )
    }
}
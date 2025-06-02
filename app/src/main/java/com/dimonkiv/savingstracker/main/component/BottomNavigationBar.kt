package com.dimonkiv.savingstracker.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.core.Screen
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme
import com.dimonkiv.savingstracker.main.model.BottomItemModel

@Composable
fun BottomNavigationBar(
    items: List<BottomItemModel>,
    currentDestination: NavDestination?,
    barHeight: Dp = 80.dp,
    fabSize: Dp = 64.dp,
    fabIconRes: Int = R.drawable.ic_add,
    cardElevation: Dp = 10.dp,
    onItemClick: (Screen) -> Unit
) {
    Box(
        modifier = Modifier
            .background(AppTheme.appColorScheme.background)
            .fillMaxWidth()
            .height(barHeight + fabSize / 2)
    ) {
        // Card container
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(barHeight)
                .align(Alignment.BottomCenter)
                .shadow(elevation = cardElevation),
            colors = CardDefaults.cardColors(containerColor = AppTheme.appColorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // avoid double shadow
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                items.forEachIndexed { index, item ->
                    val isSelected = currentDestination
                        ?.hierarchy
                        ?.any { it.route == item.route.name } == true

                    NavigationBarItem(
                        onClick = { onItemClick(item.route) },
                        selected = isSelected,
                        icon = {
                            Icon(
                                painter = painterResource(id = item.iconRes),
                                contentDescription = null,
                                modifier = Modifier.size(24.dp),
                                tint = if (isSelected)
                                    AppTheme.appColorScheme.primary
                                else
                                    AppTheme.appColorScheme.textSecondary
                            )
                        },
                        label = {
                            Text(
                                text = item.title,
                                color = if (isSelected)
                                    AppTheme.appColorScheme.primary
                                else
                                    AppTheme.appColorScheme.textSecondary
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent
                        )
                    )

                    // Space for the floating action button
                    if (index == 1) {
                        Spacer(modifier = Modifier.size(fabSize))
                    }
                }
            }
        }

        // Floating Action Button (FAB)
        Fab(
            modifier = Modifier.align(Alignment.TopCenter),
            fabSize = fabSize,
            fabColor = AppTheme.appColorScheme.primary,
            iconRes = fabIconRes
        ) {
            onItemClick(Screen.ADD_TRANSACTION)
        }
    }
}
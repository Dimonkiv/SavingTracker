package com.dimonkiv.savingstracker.main.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.core.Screen
import com.dimonkiv.savingstracker.core.design_system.Dark
import com.dimonkiv.savingstracker.core.design_system.LightDark
import com.dimonkiv.savingstracker.core.design_system.Purple
import com.dimonkiv.savingstracker.main.model.BottomItemModel

@Composable
fun BottomNavigationBar(
    items: List<BottomItemModel>,
    currentDestination: NavDestination?,
    barHeight: Dp = 60.dp,
    fabSize: Dp = 64.dp,
    fabIconRes: Int = R.drawable.ic_add,
    cardTopCornerSize: Dp = 24.dp,
    cardElevation: Dp = 8.dp,
    onItemClick: (Screen) -> Unit
) {
    Box(
        modifier = Modifier
            .background(Dark)
            .fillMaxWidth()
            .height(barHeight + fabSize / 2)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(barHeight)
                .align(Alignment.BottomCenter),
            border = BorderStroke(2.dp, LightDark),
            colors = CardDefaults.cardColors(containerColor = Dark),
            elevation = CardDefaults.cardElevation(defaultElevation = cardElevation),
            shape = RoundedCornerShape(topStart = cardTopCornerSize, topEnd = cardTopCornerSize)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                items.forEachIndexed { pos: Int, item: BottomItemModel ->
                    BottomBarItem(
                        item = item,
                        currentDestination = currentDestination
                    ) {
                        onItemClick(item.route)
                    }

                    if (pos == 1) {
                        Spacer(modifier = Modifier.size(fabSize))
                    }
                }
            }
        }

        Fab(
            modifier = Modifier.align(Alignment.TopCenter),
            fabSize = fabSize,
            fabColor = Purple,
            iconRes = fabIconRes
        ) {
            onItemClick(Screen.ADD)
        }


    }

}
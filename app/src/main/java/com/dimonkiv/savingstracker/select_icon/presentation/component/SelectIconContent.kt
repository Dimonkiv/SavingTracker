package com.dimonkiv.savingstracker.select_icon.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dimonkiv.savingstracker.core.design_system.LightDark
import com.dimonkiv.savingstracker.core.design_system.LightGray
import com.dimonkiv.savingstracker.core.design_system.Spacing
import com.dimonkiv.savingstracker.select_icon.presentation.model.SelectedIconModel

@Composable
fun SelectIconContent(
    item: SelectedIconModel,
    onColorSelected: (Color) -> Unit,
    onIconSelected: (Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconBox(
            outlineContainerColor = LightDark,
            containerColor = item.selectedColor,
            outlineContainerSize = 140.dp,
            containerSize = 120.dp,
            iconSize = 50.dp,
            iconRes = item.selectedIcon
        )

        Spacer(modifier = Modifier.size(Spacing.L))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(LightDark)
                .padding(Spacing.L)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Color",
                    fontSize = 18.sp,
                    color = LightGray
                )
                Spacer(modifier = Modifier.size(Spacing.M))

                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    columns = GridCells.Adaptive(minSize = 50.dp),
                    verticalArrangement = Arrangement.spacedBy(Spacing.S),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.S)
                ) {
                    items(item.colors.size) {
                        val color = item.colors[it]

                        IconBox(
                            outlineContainerColor = if (color.selected) LightGray else LightDark,
                            containerColor = color.color,
                            outlineContainerSize = 50.dp,
                            containerSize = 40.dp,
                            iconSize = 50.dp,
                            clickable = true,
                            onIconClick = {
                                onColorSelected(color.color)
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.size(Spacing.M))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(LightGray)
                )

                Spacer(modifier = Modifier.size(Spacing.M))

                Text(
                    text = "Icon",
                    fontSize = 18.sp,
                    color = LightGray
                )
                Spacer(modifier = Modifier.size(Spacing.M))

                LazyVerticalGrid(
                    modifier = Modifier.fillMaxWidth(),
                    columns = GridCells.Adaptive(minSize = 50.dp),
                    verticalArrangement = Arrangement.spacedBy(Spacing.S),
                    horizontalArrangement = Arrangement.spacedBy(Spacing.S)
                ) {
                    items(item.icons.size) {
                        val icon = item.icons[it]

                        IconBox(
                            outlineContainerColor = if (icon.selected) LightGray else LightDark,
                            containerColor = item.selectedColor,
                            outlineContainerSize = 50.dp,
                            containerSize = 40.dp,
                            iconSize = 24.dp,
                            iconRes = icon.iconRes,
                            clickable = true,
                            onIconClick = {
                                onIconSelected(icon.iconRes)
                            }
                        )
                    }
                }
            }


        }
    }
}
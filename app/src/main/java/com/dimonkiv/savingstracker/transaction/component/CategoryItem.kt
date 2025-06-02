package com.dimonkiv.savingstracker.transaction.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme
import com.dimonkiv.savingstracker.designsystem.theme.Spacing

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    text: String = "",
    @DrawableRes
    iconRes: Int = -1,
    tint: Color = AppTheme.appColorScheme.onPrimary,
    iconBackgroundColor: Color = AppTheme.appColorScheme.primary,
    onCategoryClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .background(AppTheme.appColorScheme.surface)
            .border(1.dp, AppTheme.appColorScheme.textPrimary, RoundedCornerShape(5.dp))
            .padding(12.dp)
            .clickable {
                onCategoryClicked()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(iconBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            if (iconRes != -1) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(iconRes),
                    tint = tint,
                    contentDescription = "icon"
                )
            }
        }

        Spacer(modifier = Modifier.size(Spacing.M))

        Text(
            text = text,
            style = AppTheme.appTypography.body1
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            modifier = Modifier.size(12.dp),
            painter = painterResource(R.drawable.ic_arrow),
            tint = AppTheme.appColorScheme.onSurface,
            contentDescription = "arrow"
        )
    }
}

@Composable
@Preview
fun CategoryItemPreview() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        CategoryItem(
            text = "Item with only icon",
            iconRes = R.drawable.ic_calendar,
            iconBackgroundColor = AppTheme.appColorScheme.surface,
            tint = AppTheme.appColorScheme.primary
        ) {}

        Spacer(modifier = Modifier.size(10.dp))

        CategoryItem(
            text = "Item without icon"
        ) {}

        Spacer(modifier = Modifier.size(10.dp))

        CategoryItem(
            text = "Item with selected icon",
            iconRes = R.drawable.ic_calendar,
        ) {}
    }
}
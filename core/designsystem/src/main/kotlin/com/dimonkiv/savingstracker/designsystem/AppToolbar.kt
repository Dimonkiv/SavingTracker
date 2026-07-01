package com.dimonkiv.savingstracker.designsystem

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dimonkiv.savingstracker.core.designsystem.R
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme
import com.dimonkiv.savingstracker.designsystem.theme.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolbar(
    title: String,
    @DrawableRes
    iconRes: Int = R.drawable.ic_back,
    onNavigationIconClicked: () -> Unit,
    actions: @Composable (RowScope.() -> Unit) = {}
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.padding(start = Spacing.L),
                text = title,
                style = AppTheme.appTypography.heading
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier
                    .padding(start = Spacing.L)
                    .size(24.dp)
                    .clickable {
                        onNavigationIconClicked()
                    },
                painter = painterResource(iconRes),
                contentDescription = "Back icon",
                tint = AppTheme.appColorScheme.textPrimary
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppTheme.appColorScheme.surface
        ),
        actions = actions
    )
}

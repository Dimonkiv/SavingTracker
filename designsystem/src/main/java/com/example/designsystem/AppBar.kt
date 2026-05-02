package com.dimonkiv.savingstracker.designsystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme

@Composable
fun AppBar(
    title: String,
    iconRes: Int = R.drawable.ic_back,
    onBackButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(AppTheme.appColorScheme.surface),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                onBackButtonClick()
            }
        ) {
            Icon(
                modifier = Modifier.size(32.dp),
                painter = painterResource(id = iconRes),
                contentDescription = "Back",
                tint = AppTheme.appColorScheme.textPrimary
            )
        }

        Text(
            text = title,
            style = AppTheme.appTypography.heading
        )

        Spacer(modifier = Modifier.size(32.dp))
    }
}

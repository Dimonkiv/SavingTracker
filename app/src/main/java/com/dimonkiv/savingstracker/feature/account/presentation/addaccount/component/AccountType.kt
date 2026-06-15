package com.dimonkiv.savingstracker.feature.account.presentation.addaccount.component

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
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

@Composable
fun AccountType(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int = R.drawable.ic_add,
    color: Color = AppTheme.appColorScheme.surface
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(color)
            .border(2.dp, AppTheme.appColorScheme.textPrimary, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = AppTheme.appColorScheme.textPrimary
        )
    }

}

@Preview
@Composable
fun AccountTypePreview() {
    Column {
        AccountType(modifier = Modifier.size(140.dp))
        AccountType(
            modifier = Modifier.size(140.dp),
            color = AppTheme.appColorScheme.orange,
            iconRes = R.drawable.ic_gas
        )
    }
}
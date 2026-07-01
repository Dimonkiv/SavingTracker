package com.dimonkiv.savingstracker.designsystem

import androidx.annotation.DrawableRes
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean = true,
    title: String = "",
    @DrawableRes leadingIcon: Int? = null,
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.appColorScheme.primary,
            disabledContainerColor = AppTheme.appColorScheme.inversePrimary
        ),
        shape = RoundedCornerShape(10.dp),
        enabled = enabled,
        onClick = {
            onClick()
        }
    ) {
        if (leadingIcon != null) {
            Icon(
                painter = painterResource(leadingIcon),
                contentDescription = null,
                tint = AppTheme.appColorScheme.onPrimary
            )
        } else {
            Text(
                text = title,
                color = AppTheme.appColorScheme.onPrimary,
                style = AppTheme.appTypography.heading
            )
        }
    }
}

@Composable
@Preview
fun AppButtonPreview() {
    AppButton(title = "Some title", onClick = {}, enabled = false)
}
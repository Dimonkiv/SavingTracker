package com.dimonkiv.savingstracker.feature.account.presentation.accounts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme
import com.dimonkiv.savingstracker.designsystem.theme.LightGray
import com.dimonkiv.savingstracker.designsystem.theme.Spacing
import com.dimonkiv.savingstracker.feature.account.presentation.accounts.model.AccountModel

@Composable
fun Account(
    account: AccountModel,
    isClickable: Boolean = false,
    onAccountSelected: (AccountModel) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(Spacing.M)
            .clickable(isClickable) {
                onAccountSelected(account)
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(account.color),
                    contentAlignment = Alignment.Center
                ) {
                     Icon(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = account.iconRes),
                        contentDescription = null,
                        tint = LightGray
                    )
                }

                Spacer(modifier = Modifier.size(Spacing.M))

                Text(
                    text = account.title,
                    style = AppTheme.appTypography.heading
                )
            }

            Text(
                text = account.balance,
                style = AppTheme.appTypography.subheading
            )

        }
    }

}
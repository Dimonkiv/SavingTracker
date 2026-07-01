package com.dimonkiv.savingstracker.feature.account.presentation.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.designsystem.ErrorDialog
import com.dimonkiv.savingstracker.designsystem.ProgressBar
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme
import com.dimonkiv.savingstracker.designsystem.theme.Spacing
import com.dimonkiv.savingstracker.feature.account.presentation.accounts.components.Accounts
import com.dimonkiv.savingstracker.feature.account.presentation.accounts.components.EmptyAccounts

@Composable
fun AccountsScreen(
    state: AccountsState,
    onAddClick: () -> Unit,
    onErrorDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(Spacing.SM)
            .fillMaxSize()
            .background(AppTheme.appColorScheme.background)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.my_accounts),
                    style = AppTheme.appTypography.heading
                )
                IconButton(
                    modifier = Modifier.size(36.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(AppTheme.appColorScheme.primary),
                    onClick = { onAddClick() },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = AppTheme.appColorScheme.onPrimary
                    ),
                    content = {
                        Icon(
                            painter = painterResource(R.drawable.ic_add),
                            contentDescription = stringResource(R.string.add_account)
                        )
                    }
                )
            }

            if (state.isLoading) {
                ProgressBar()
            }

            if (state.types.isEmpty() && !state.isLoading) {
                EmptyAccounts()
            } else {
                Accounts(
                    totalBalance = state.totalBalance,
                    types = state.types
                )
            }
        }
    }

    if (state.error != null) {
        ErrorDialog(
            message = state.error,
            onClick = onErrorDismiss
        )
    }
}
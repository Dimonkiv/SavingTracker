package com.dimonkiv.savingstracker.feature.transaction.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme
import com.dimonkiv.savingstracker.feature.account.presentation.accounts.components.Account
import com.dimonkiv.savingstracker.feature.account.presentation.accounts.model.AccountModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectAccountBottomSheet(
    isVisible: Boolean,
    accounts: List<AccountModel>,
    onDismissRequest: () -> Unit,
    onAccountSelected: (AccountModel) -> Unit
) {
    if (!isVisible) return

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .navigationBarsPadding()
        ) {
            Text(
                text = stringResource(R.string.select_account),
                style = AppTheme.appTypography.heading,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(accounts.size) {
                    val account = accounts.getOrNull(it) ?: return@items
                    Account(
                        account = account,
                        isClickable = true,
                        onAccountSelected = {
                            onAccountSelected(it)
                        }
                    )
                }
            }
        }
    }
}
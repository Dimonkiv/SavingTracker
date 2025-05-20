package com.dimonkiv.savingstracker.account.presentation.accounts.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dimonkiv.savingstracker.account.presentation.accounts.model.AccountsModel
import com.dimonkiv.savingstracker.designsystem.theme.Spacing

@Composable
fun Accounts(
    accounts: AccountsModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(Spacing.L))
        Text(
            text = accounts.totalBalance,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.size(Spacing.L))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Spacing.M)
        ) {
            items(accounts.types.size) { pos ->
                AccountGroup(accounts.types[pos])
            }
        }
    }
}
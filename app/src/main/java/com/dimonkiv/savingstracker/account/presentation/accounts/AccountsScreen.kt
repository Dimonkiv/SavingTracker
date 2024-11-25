package com.dimonkiv.savingstracker.account.presentation.accounts

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.account.presentation.accounts.components.Accounts
import com.dimonkiv.savingstracker.account.presentation.accounts.components.EmptyAccounts
import com.dimonkiv.savingstracker.core.design_system.Dark
import com.dimonkiv.savingstracker.core.design_system.ErrorDialog
import com.dimonkiv.savingstracker.core.design_system.LightGray
import com.dimonkiv.savingstracker.core.design_system.ProgressBar
import com.dimonkiv.savingstracker.core.design_system.Spacing

@Composable
fun AccountsScreen(
    state: AccountState,
    onOkErrorClick: () -> Unit,
    onAddClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(Spacing.SM)
            .fillMaxSize()
            .background(Dark)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(48.dp))
                Text(
                    text = "Accounts",
                    color = LightGray,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                IconButton(
                    modifier = Modifier.size(48.dp),
                    onClick = { onAddClick() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_square_add),
                        contentDescription = "Add",
                        tint = LightGray
                    )
                }
            }

            when (state) {
                is AccountState.Idle -> EmptyAccounts(onAddClick)
                is AccountState.Loading -> ProgressBar()
                is AccountState.Success -> Accounts(state.model)
                is AccountState.Error -> {
                    Log.d("Test", "Again error")
                    ErrorDialog(message = state.message) {
                        onOkErrorClick()
                    }
                }
            }


        }
    }
}
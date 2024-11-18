package com.dimonkiv.savingstracker.presentation.account.accounts

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
import com.dimonkiv.savingstracker.presentation.account.accounts.components.Accounts
import com.dimonkiv.savingstracker.presentation.account.accounts.components.EmptyAccounts
import com.dimonkiv.savingstracker.presentation.core.design_system.Dark
import com.dimonkiv.savingstracker.presentation.core.design_system.ErrorDialog
import com.dimonkiv.savingstracker.presentation.core.design_system.LightGray
import com.dimonkiv.savingstracker.presentation.core.design_system.ProgressBar
import com.dimonkiv.savingstracker.presentation.core.design_system.Spacing

@Composable
fun AccountsScreen(
    state: AccountContract.AccountState,
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
                is AccountContract.AccountState.Idle -> EmptyAccounts(onAddClick)
                is AccountContract.AccountState.Loading -> ProgressBar()
                is AccountContract.AccountState.Success -> Accounts(state.model)
                is AccountContract.AccountState.Error -> {
                    Log.d("Test", "Again error")
                    ErrorDialog(message = state.message) {
                        onOkErrorClick()
                    }
                }
            }


        }
    }
}
package com.dimonkiv.savingstracker.presentation.account.accounts.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.presentation.core.design_system.LightGray
import com.dimonkiv.savingstracker.presentation.core.design_system.Purple
import com.dimonkiv.savingstracker.presentation.core.design_system.Spacing

@Composable
fun EmptyAccounts() {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = R.drawable.ic_money_bag),
                contentDescription = null,
                tint = LightGray
            )
            Spacer(modifier = Modifier.size(Spacing.S))
            Text(
                text = "You don't have accounts",
                color = LightGray,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.size(Spacing.SM))
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Purple),
                onClick = { }) {
                Text(
                    text = "Add account",
                    fontSize = 20.sp
                )
            }
        }
    }
}
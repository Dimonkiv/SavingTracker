package com.dimonkiv.savingstracker.presentation.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.presentation.accounts.components.Accounts
import com.dimonkiv.savingstracker.presentation.design_system.Dark
import com.dimonkiv.savingstracker.presentation.design_system.LightGray
import com.dimonkiv.savingstracker.presentation.design_system.ProgressBar
import com.dimonkiv.savingstracker.presentation.design_system.Spacing

@Composable
fun AccountsScreen() {
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
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "My accounts",
                        color = LightGray,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                IconButton(modifier = Modifier.weight(0.1f), onClick = { /*TODO*/ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_square_add),
                        contentDescription = "Add",
                        tint = LightGray
                    )
                }
            }
            Accounts()
        }
    }
}
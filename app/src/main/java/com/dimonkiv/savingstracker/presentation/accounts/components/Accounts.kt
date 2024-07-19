package com.dimonkiv.savingstracker.presentation.accounts.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dimonkiv.savingstracker.presentation.design_system.LightGray
import com.dimonkiv.savingstracker.presentation.design_system.Spacing

@Composable
fun Accounts() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Total balance")
        Spacer(modifier = Modifier.size(Spacing.S))
        Text(
            text = "$2000",
            color = LightGray,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.size(Spacing.L))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(Spacing.SM)
        ) {
            items(5) {
                Account()
            }
        }
    }
}
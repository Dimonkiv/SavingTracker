package com.dimonkiv.savingstracker.transaction.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme
import com.dimonkiv.savingstracker.designsystem.theme.Spacing

@Composable
fun AddTransfer() {
    var balance = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.XL)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Spacing.XL),
            value = balance.value,
            onValueChange = {
                balance.value = it
            },
            label = {
                Text(text = "Balance")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = AppTheme.appColorScheme.surface,
                unfocusedContainerColor = AppTheme.appColorScheme.surface
            )
        )

        CategoryItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Spacing.XL),
            text = "Select date",
            iconRes = R.drawable.ic_calendar,
            tint = AppTheme.appColorScheme.primary,
            iconBackgroundColor = AppTheme.appColorScheme.surface,
            onCategoryClicked = {

            }
        )

        CategoryItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Spacing.XL),
            text = "Select account",
            onCategoryClicked = {

            }
        )

        CategoryItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Spacing.XL),
            text = "Select category",
            onCategoryClicked = {

            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Spacing.XL),
            value = balance.value,
            onValueChange = {
                balance.value = it
            },
            label = {
                Text(text = "Note")
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = AppTheme.appColorScheme.surface,
                unfocusedContainerColor = AppTheme.appColorScheme.surface
            )
        )
    }
}
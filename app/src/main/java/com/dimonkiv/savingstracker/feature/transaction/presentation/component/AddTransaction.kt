package com.dimonkiv.savingstracker.feature.transaction.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme
import com.dimonkiv.savingstracker.designsystem.theme.Spacing
import com.dimonkiv.savingstracker.feature.transaction.presentation.Intent
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.AddTransactionUiModel

@Composable
fun AddTransaction(
    state: AddTransactionUiModel,
    onSelectDateClicked: () -> Unit,
    onSelectAccountClicked: () -> Unit,
    onEventChanged: (Intent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Spacing.XL)
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Spacing.XL),
            value = state.balance,
            onValueChange = {
                onEventChanged(Intent.OnBalanceTextChanged(it))
            },
            label = {
                Text(text = stringResource(R.string.balance_hint))
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
            text = state.date,
            iconRes = R.drawable.ic_calendar,
            tint = AppTheme.appColorScheme.primary,
            iconBackgroundColor = AppTheme.appColorScheme.surface,
            onCategoryClicked = {
                onSelectDateClicked()
            }
        )

        CategoryItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Spacing.XL),
            text = stringResource(R.string.select_account),
            onCategoryClicked = {
                onSelectAccountClicked()
            }
        )

        CategoryItem(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Spacing.XL),
            text = stringResource(R.string.select_category),
            onCategoryClicked = {

            }
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = Spacing.XL),
            value = state.note,
            onValueChange = {
                onEventChanged(Intent.OnNoteTextChanged(it))
            },
            label = {
                Text(text = stringResource(R.string.note_hint))
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = AppTheme.appColorScheme.surface,
                unfocusedContainerColor = AppTheme.appColorScheme.surface
            )
        )
    }
}
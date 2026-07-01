package com.dimonkiv.savingstracker.feature.select_icon.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dimonkiv.savingstracker.designsystem.AppButton
import com.dimonkiv.savingstracker.designsystem.theme.Spacing
import com.dimonkiv.savingstracker.feature.select_icon.presentation.SelectIconContract.Intent
import com.dimonkiv.savingstracker.feature.select_icon.presentation.component.SelectIconContent
import com.dimonkiv.savingstracker.feature.select_icon.presentation.model.SelectedIconModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectIconScreen(
    state: SelectedIconModel,
    onIntent: (Intent) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(Spacing.L))

            SelectIconContent(
                item = state,
                onColorSelected = {
                    onIntent(Intent.OnColorSelected(it))
                },
                onIconSelected = {
                    onIntent(Intent.OnIconSelected(it))
                }
            )
        }

        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(
                    start = Spacing.XL,
                    end = Spacing.XL,
                    bottom = 60.dp
                ),
            enabled = state.buttonEnabled,
            onClick = {
                onIntent(Intent.OnSelectButtonClick)
            },
            title = "Select"
        )
    }
}
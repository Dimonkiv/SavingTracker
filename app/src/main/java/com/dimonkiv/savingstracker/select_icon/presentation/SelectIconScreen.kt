package com.dimonkiv.savingstracker.select_icon.presentation

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
import com.dimonkiv.savingstracker.designsystem.ProgressBar
import com.dimonkiv.savingstracker.designsystem.theme.Spacing
import com.dimonkiv.savingstracker.select_icon.presentation.SelectIconContract.Event
import com.dimonkiv.savingstracker.select_icon.presentation.component.SelectIconContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectIconScreen(
    state: SelectIconContract.State,
    onEventChanged: (Event) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(Spacing.L))

            when (state.state) {
                is SelectIconContract.SelectIconState.Loading -> ProgressBar()
                is SelectIconContract.SelectIconState.Idle -> ProgressBar()
                is SelectIconContract.SelectIconState.Success -> {
                    SelectIconContent(
                        item = state.state.state,
                        onColorSelected = {
                            onEventChanged(Event.OnColorSelected(it))
                        },
                        onIconSelected = {
                            onEventChanged(Event.OnIconSelected(it))
                        }
                    )
                }
            }
        }

        val buttonEnabled = if (state.state is SelectIconContract.SelectIconState.Success) {
            state.state.state.buttonEnabled
        } else {
            false
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
            enabled = buttonEnabled,
            onClick = {
                onEventChanged(Event.OnSelectButtonClick)
            },
            title = "Select"
        )
    }
}
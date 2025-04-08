package com.dimonkiv.savingstracker.select_icon.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dimonkiv.savingstracker.core.design_system.AppBar
import com.dimonkiv.savingstracker.core.design_system.LightGray
import com.dimonkiv.savingstracker.core.design_system.ProgressBar
import com.dimonkiv.savingstracker.core.design_system.Purple
import com.dimonkiv.savingstracker.core.design_system.Spacing
import com.dimonkiv.savingstracker.select_icon.presentation.component.SelectIconContent

@Composable
fun SelectIconScreen(
    state: SelectIconContract.State,
    onBackButtonClick: () -> Unit,
    onColorSelected: (Color) -> Unit,
    onIconSelected: (Int) -> Unit,
    onSelectButtonClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 55.dp, start = Spacing.L, end = Spacing.L)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppBar(title = "Select icon") {
                onBackButtonClick()
            }
            Spacer(modifier = Modifier.size(Spacing.L))

            when (state.state) {
                is SelectIconContract.SelectIconState.Loading -> ProgressBar()
                is SelectIconContract.SelectIconState.Idle -> ProgressBar()
                is SelectIconContract.SelectIconState.Success -> {
                    SelectIconContent(
                        item = state.state.state,
                        onColorSelected = {
                            onColorSelected(it)
                        },
                        onIconSelected = {
                            onIconSelected(it)
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

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp),
            enabled = buttonEnabled,
            onClick = {
                onSelectButtonClick()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple,
                disabledContainerColor = Purple.copy(0.6f),
                disabledContentColor = LightGray.copy(0.6f)
            )
        ) {
            Text(
                text = "Select",
                fontSize = 24.sp
            )
        }
    }

}
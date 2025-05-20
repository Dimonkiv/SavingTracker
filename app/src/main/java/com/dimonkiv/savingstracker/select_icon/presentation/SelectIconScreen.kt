package com.dimonkiv.savingstracker.select_icon.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.designsystem.AppBar
import com.dimonkiv.savingstracker.designsystem.AppButton
import com.dimonkiv.savingstracker.designsystem.theme.LightGray
import com.dimonkiv.savingstracker.designsystem.ProgressBar
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme
import com.dimonkiv.savingstracker.designsystem.theme.Purple
import com.dimonkiv.savingstracker.designsystem.theme.Spacing
import com.dimonkiv.savingstracker.select_icon.presentation.component.SelectIconContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectIconScreen(
    state: SelectIconContract.State,
    onBackButtonClick: () -> Unit,
    onColorSelected: (Color) -> Unit,
    onIconSelected: (Int) -> Unit,
    onSelectButtonClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(start = Spacing.L),
                        text = "Select icon",
                        style = AppTheme.appTypography.heading
                    )
                },
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(start = Spacing.L)
                            .size(24.dp)
                            .clickable {
                                onBackButtonClick()
                            },
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = "Back icon",
                        tint = AppTheme.appColorScheme.textPrimary
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppTheme.appColorScheme.surface
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
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
                    onSelectButtonClick()
                },
                title = "Select"
            )
        }
    }
}
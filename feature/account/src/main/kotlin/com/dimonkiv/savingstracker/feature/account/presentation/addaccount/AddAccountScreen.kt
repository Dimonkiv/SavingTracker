package com.dimonkiv.savingstracker.feature.account.presentation.addaccount

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dimonkiv.savingstracker.core.designsystem.R
import com.dimonkiv.savingstracker.designsystem.AppButton
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme
import com.dimonkiv.savingstracker.designsystem.theme.LightGray
import com.dimonkiv.savingstracker.designsystem.theme.Spacing
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.SelectAccountTypeRoute
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.model.AddAccountModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAccountScreen(
    model: AddAccountModel,
    sheetState: SheetState,
    showBottomSheet: Boolean,
    onIntent: (AddAccountIntent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(Spacing.L))

        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .clickable {
                    onIntent(AddAccountIntent.OnSelectIconClicked)
                },
            contentAlignment = Alignment.Center

        ) {
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(AppTheme.appColorScheme.primary),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .clip(CircleShape)
                        .background(model.color),
                    contentAlignment = Alignment.Center
                ) {
                    if (model.iconRes != -1) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(id = model.iconRes),
                            contentDescription = null,
                            tint = LightGray
                        )
                    } else {
                        Text(
                            text = stringResource(R.string.add_icon),
                            color = AppTheme.appColorScheme.onPrimary,
                            style = AppTheme.appTypography.caption,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .padding(bottom = 15.dp, end = 15.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(AppTheme.appColorScheme.primary)
                    .align(Alignment.BottomEnd),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = stringResource(R.string.edit),
                    tint = AppTheme.appColorScheme.onPrimary
                )
            }
        }

        Spacer(modifier = Modifier.size(Spacing.L))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Spacing.XL, end = Spacing.XL, bottom = Spacing.L),
            value = model.title,
            onValueChange = {
                onIntent(AddAccountIntent.OnTitleTextChanged(it))
            },
            label = {
                Text(text = stringResource(R.string.title))
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = AppTheme.appColorScheme.surface,
                unfocusedContainerColor = AppTheme.appColorScheme.surface
            )
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Spacing.XL, end = Spacing.XL, bottom = Spacing.L)
                .background(AppTheme.appColorScheme.surface)
                .border(1.dp, AppTheme.appColorScheme.textPrimary)
                .padding(15.dp)
                .clickable {
                    onIntent((AddAccountIntent.OnTypeClicked))
                },
        ) {
            if (model.type.title.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(25.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(model.type.color)
                )
                Spacer(modifier = Modifier.size(10.dp))
            }
            Text(
                text = model.type.title.ifEmpty { stringResource(R.string.type) },
                color = AppTheme.appColorScheme.textPrimary
            )
        }


        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Spacing.XL, end = Spacing.XL, bottom = Spacing.XL),
            value = model.balance,
            onValueChange = {
                onIntent(AddAccountIntent.OnBalanceTextChanged(it))
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

        Spacer(modifier = Modifier.weight(1f))

        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = Spacing.XL,
                    end = Spacing.XL,
                    bottom = 60.dp
                ),
            enabled = model.isButtonEnabled,
            title = stringResource(R.string.create_account),
            onClick = {
                onIntent(AddAccountIntent.OnCreateButtonClicked)
            }
        )
    }

    if (showBottomSheet) {
        SelectAccountTypeRoute(
            sheetState = sheetState,
            onTypeSelect = {
                onIntent(AddAccountIntent.OnTypeSelected(it))
            },
            onDismissBottomSheet = {
                onIntent(AddAccountIntent.OnDismissButtonClick)
            }
        )
    }
}
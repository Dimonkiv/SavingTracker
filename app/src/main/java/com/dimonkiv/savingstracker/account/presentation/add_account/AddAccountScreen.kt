package com.dimonkiv.savingstracker.account.presentation.add_account

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dimonkiv.savingstracker.account.presentation.add_account.account_type.SelectAccountTypeRoute
import com.dimonkiv.savingstracker.account.presentation.add_account.model.AddAccountModel
import com.dimonkiv.savingstracker.core.design_system.AppBar
import com.dimonkiv.savingstracker.core.design_system.Dark
import com.dimonkiv.savingstracker.core.design_system.LightDark
import com.dimonkiv.savingstracker.core.design_system.LightGray
import com.dimonkiv.savingstracker.core.design_system.Purple
import com.dimonkiv.savingstracker.core.design_system.Spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAccountScreen(
    model: AddAccountModel,
    sheetState: SheetState,
    showBottomSheet: Boolean,
    onEventChanged: (Event) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 55.dp, start = Spacing.L, end = Spacing.L),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppBar(title = "Add account") {
            onEventChanged(Event.OnBackButtonClicked)
        }

        Spacer(modifier = Modifier.size(Spacing.L))

        Box(
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
                .background(Dark)
                .clickable {
                    onEventChanged(Event.OnSelectIconClicked)
                },
            contentAlignment = Alignment.Center

        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(LightDark),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
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
                            text = "Add icon",
                            color = LightGray,
                            fontSize = 20.sp,
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
                    .background(LightDark)
                    .align(Alignment.BottomEnd),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = LightGray
                )
            }
        }

        Spacer(modifier = Modifier.size(Spacing.L))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = model.title,
            onValueChange = {
                onEventChanged(Event.OnTitleTextChanged(it))
            },
            label = {
                Text(text = "Title")
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightDark,
                unfocusedContainerColor = LightDark,
                focusedTextColor = LightGray,
                unfocusedTextColor = LightGray,
                unfocusedLabelColor = LightGray,
                focusedLabelColor = LightGray
            )
        )

        Spacer(modifier = Modifier.size(Spacing.L))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(LightDark)
                .padding(15.dp)
                .clickable {
                    onEventChanged((Event.OnTypeClicked))
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
                text = model.type.title.ifEmpty { "Type" },
                color = LightGray
            )
        }

        Spacer(modifier = Modifier.size(Spacing.L))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = model.balance,
            onValueChange = {
                onEventChanged(Event.OnBalanceTextChanged(it))
            },
            label = {
                Text(text = "Balance")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = LightDark,
                unfocusedContainerColor = LightDark,
                focusedTextColor = LightGray,
                unfocusedTextColor = LightGray,
                unfocusedLabelColor = LightGray,
                focusedLabelColor = LightGray
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier.fillMaxWidth()
                .padding(bottom = 60.dp),
            enabled = model.isButtonEnabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple,
                disabledContainerColor = Purple.copy(alpha = 0.4f),
                contentColor = Color.White,
                disabledContentColor = Color.White.copy(0.4f)
            ),
            onClick = {
                onEventChanged(Event.OnCreateButtonClicked)
            }
        ) {
            Text(
                text = "Create account",
                fontSize = 20.sp
            )
        }

    }

    if (showBottomSheet) {
        SelectAccountTypeRoute(
            sheetState = sheetState,
            onTypeSelect = {
                onEventChanged(Event.OnTypeSelected(it))
            },
            onDismissBottomSheet = {
                onEventChanged(Event.OnDismissButtonClick)
            }
        )
    }
}
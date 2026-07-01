package com.dimonkiv.savingstracker.designsystem

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.dimonkiv.savingstracker.core.designsystem.R

@Composable
fun ErrorDialog(
    message: String,
    onClick: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                painter = painterResource(R.drawable.ic_error),
                contentDescription = null
            )
        },
        title = {
            Text(text = stringResource(R.string.error))
        },
        text = {
            Text(text = message)
        },
        onDismissRequest = { onClick() },
        confirmButton = {
            Button(onClick = { onClick() }) {
                Text(text = stringResource(R.string.ok))
            }
        }
    )
}
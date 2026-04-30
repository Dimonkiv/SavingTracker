package com.dimonkiv.savingstracker.designsystem

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

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
            Text(text = "Error")
        },
        text = {
            Text(text = message)
        },
        onDismissRequest = {
           onClick()
        },
        confirmButton = {
            Button(
                onClick = {
                    onClick()
                }
            ) {
                Text(text = "Ok")
            }
        }
    )
}

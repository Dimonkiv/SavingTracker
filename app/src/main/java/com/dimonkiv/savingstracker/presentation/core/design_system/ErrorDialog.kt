package com.dimonkiv.savingstracker.presentation.core.design_system

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorDialog(
    message: String,
    onClick: () -> Unit
) {
    AlertDialog(
        icon = {
            Icon(
                imageVector = Icons.Default.Build,
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
package com.dimonkiv.savingstracker.designsystem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dimonkiv.savingstracker.core.designsystem.R
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectDateBottomSheet(
    isVisible: Boolean,
    onDismissRequest: () -> Unit,
    initialDateMillis: Long? = null,
    onDateSelected: (Long) -> Unit,
    confirmButtonText: String = "OK",
    cancelButtonText: String = "Cancel"
) {
    if (!isVisible) return

    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = initialDateMillis)
    val confirmEnabled = datePickerState.selectedDateMillis != null

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .navigationBarsPadding()
        ) {
            Text(
                text = stringResource(R.string.select_date),
                style = AppTheme.appTypography.heading,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            DatePicker(state = datePickerState)

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDismissRequest) {
                    Text(cancelButtonText)
                }
                Spacer(modifier = Modifier.width(8.dp))
                TextButton(
                    onClick = {
                        datePickerState.selectedDateMillis?.let {
                            onDateSelected(it)
                        }
                        onDismissRequest()
                    },
                    enabled = confirmEnabled
                ) {
                    Text(confirmButtonText)
                }
            }
        }
    }
}

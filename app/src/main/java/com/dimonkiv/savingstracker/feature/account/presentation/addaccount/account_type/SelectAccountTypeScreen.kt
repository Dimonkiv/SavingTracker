package com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme
import com.dimonkiv.savingstracker.designsystem.theme.LightGray
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.component.AccountTypeScreen
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.AccountTypeModel

@ExperimentalMaterial3Api
@Composable
fun SelectAccountTypeScreen(
    sheetState: SheetState,
    types: List<AccountTypeModel>,
    onTypeSelect: (AccountTypeModel) -> Unit,
    onDismissBottomSheet: () -> Unit
) {
    ModalBottomSheet(
        containerColor = AppTheme.appColorScheme.surface,
        contentColor = AppTheme.appColorScheme.textPrimary,
        modifier = Modifier.fillMaxWidth(),
        onDismissRequest = {
            onDismissBottomSheet()
        },
        dragHandle = {
            BottomSheetDefaults.DragHandle(color = LightGray)
        },
        sheetState = sheetState
    ) {
        Column {
            Text(
                modifier = Modifier.padding(20.dp),
                text = stringResource(R.string.select_account_type),
                fontSize = 20.sp
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(types, key = { it.id }) { type ->
                    AccountTypeScreen(type) { onTypeSelect(type) }
                }
            }
        }
    }
}

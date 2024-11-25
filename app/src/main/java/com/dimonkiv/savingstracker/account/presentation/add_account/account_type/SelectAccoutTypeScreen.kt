package com.dimonkiv.savingstracker.account.presentation.add_account.account_type

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dimonkiv.savingstracker.account.presentation.add_account.account_type.component.AccountTypeScreen
import com.dimonkiv.savingstracker.account.presentation.add_account.account_type.model.AccountTypeModel
import com.dimonkiv.savingstracker.core.design_system.LightDark
import com.dimonkiv.savingstracker.core.design_system.LightGray

@ExperimentalMaterial3Api
@Composable
fun SelectAccountTypeScreen(
    sheetState: SheetState,
    types: List<AccountTypeModel>,
    onTypeSelect: (AccountTypeModel) -> Unit,
    onDismissBottomSheet: () -> Unit
) {
    ModalBottomSheet(
        containerColor = LightDark,
        contentColor = LightGray,
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
                text = "Select account type",
                fontSize = 20.sp
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(types.size) {
                    AccountTypeScreen(types[it]) { type ->
                        onTypeSelect(type)
                    }
                }
            }
        }
    }
}
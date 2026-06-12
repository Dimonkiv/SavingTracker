package com.dimonkiv.savingstracker.account.presentation.addaccount.account_type

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dimonkiv.savingstracker.account.presentation.addaccount.account_type.model.AccountTypeModel
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectAccountTypeRoute(
    sheetState: SheetState,
    onTypeSelect: (AccountTypeModel) -> Unit,
    onDismissBottomSheet: () -> Unit,
    viewModel: SelectAccountTypeViewModel = koinViewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()

    SelectAccountTypeScreen(
        sheetState,
        state.value.types,
        onTypeSelect = {
            onTypeSelect(it)
        },
        onDismissBottomSheet = {
            onDismissBottomSheet()
        }
    )
}
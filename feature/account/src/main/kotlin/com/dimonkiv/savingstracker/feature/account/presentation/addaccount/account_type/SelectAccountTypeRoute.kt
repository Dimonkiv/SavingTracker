package com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dimonkiv.savingstracker.core.navigation.Navigator
import com.dimonkiv.savingstracker.feature.account.di.AccountTypeResultBus
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectAccountTypeRoute(
    navigator: Navigator,
    accountTypeResult: AccountTypeResultBus = koinInject(),
    viewModel: SelectAccountTypeViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    SelectAccountTypeScreen(
        types = state.types,
        onTypeSelect = { type ->
            accountTypeResult.send(type)
            navigator.goBack()
        }
    )
}
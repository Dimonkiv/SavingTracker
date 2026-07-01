package com.dimonkiv.savingstracker.feature.transaction.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dimonkiv.savingstracker.core.navigation.Navigator
import com.dimonkiv.savingstracker.feature.transaction.di.TransactionAccountResultBus
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SelectTransactionAccountRoute(
    navigator: Navigator,
    accountResult: TransactionAccountResultBus = koinInject(),
    viewModel: SelectTransactionAccountViewModel = koinViewModel()
) {
    val accounts by viewModel.accounts.collectAsStateWithLifecycle()

    SelectAccountBottomSheetContent(
        accounts = accounts,
        onAccountSelected = { account ->
            accountResult.send(account)
            navigator.goBack()
        }
    )
}

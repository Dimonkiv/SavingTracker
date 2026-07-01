package com.dimonkiv.savingstracker.feature.transaction.di

import androidx.compose.material3.ExperimentalMaterial3Api
import com.dimonkiv.savingstracker.core.navigation.BottomSheetConfig
import com.dimonkiv.savingstracker.core.navigation.BottomSheetSceneStrategy
import com.dimonkiv.savingstracker.core.navigation.routes.AddTransaction
import com.dimonkiv.savingstracker.core.navigation.routes.SelectTransactionAccount
import com.dimonkiv.savingstracker.core.navigation.routes.SelectTransactionDate
import com.dimonkiv.savingstracker.feature.transaction.presentation.AddTransactionRoute
import com.dimonkiv.savingstracker.feature.transaction.presentation.SelectTransactionAccountRoute
import com.dimonkiv.savingstracker.feature.transaction.presentation.SelectTransactionAccountViewModel
import com.dimonkiv.savingstracker.feature.transaction.presentation.SelectTransactionDateRoute
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)
val transactionNavigationModule = module {
    single { TransactionAccountResultBus() }
    single { TransactionDateResultBus() }
    viewModel { SelectTransactionAccountViewModel(get()) }

    navigation<AddTransaction> { AddTransactionRoute(navigator = get()) }
    navigation<SelectTransactionAccount>(
        metadata = BottomSheetSceneStrategy.bottomSheet(BottomSheetConfig(skipPartiallyExpanded = true))
    ) { SelectTransactionAccountRoute(navigator = get()) }
    navigation<SelectTransactionDate>(
        metadata = BottomSheetSceneStrategy.bottomSheet(BottomSheetConfig(skipPartiallyExpanded = true))
    ) { key -> SelectTransactionDateRoute(key = key, navigator = get()) }
}

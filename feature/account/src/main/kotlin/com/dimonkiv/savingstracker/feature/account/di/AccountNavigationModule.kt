package com.dimonkiv.savingstracker.feature.account.di

import androidx.compose.material3.ExperimentalMaterial3Api
import com.dimonkiv.savingstracker.core.navigation.BottomSheetConfig
import com.dimonkiv.savingstracker.core.navigation.BottomSheetSceneStrategy
import com.dimonkiv.savingstracker.core.navigation.routes.AddAccount
import com.dimonkiv.savingstracker.core.navigation.routes.Accounts
import com.dimonkiv.savingstracker.core.navigation.routes.SelectAccountType
import com.dimonkiv.savingstracker.feature.account.presentation.accounts.AccountsRoute
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.AddAccountRoute
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.SelectAccountTypeRoute
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class, ExperimentalMaterial3Api::class)
val accountNavigationModule = module {
    single { AccountTypeResultBus() }

    navigation<Accounts> { AccountsRoute(navigator = get()) }
    navigation<AddAccount> { AddAccountRoute(navigator = get()) }
    navigation<SelectAccountType>(
        metadata = BottomSheetSceneStrategy.bottomSheet(BottomSheetConfig())
    ) { SelectAccountTypeRoute(navigator = get()) }
}

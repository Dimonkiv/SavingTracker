package com.dimonkiv.savingstracker.feature.account.di

import com.dimonkiv.savingstracker.core.db.AppDatabase
import com.dimonkiv.savingstracker.feature.account.data.repository.AccountRepositoryImpl
import com.dimonkiv.savingstracker.feature.account.data.repository.AccountTypeRepositoryImpl
import com.dimonkiv.savingstracker.feature.account.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.feature.account.domain.repository.AccountTypeRepository
import com.dimonkiv.savingstracker.feature.account.domain.usecase.CreateAccountUseCase
import com.dimonkiv.savingstracker.feature.account.domain.usecase.CreateAccountUseCaseImpl
import com.dimonkiv.savingstracker.feature.account.domain.usecase.GetAccountTypesUseCase
import com.dimonkiv.savingstracker.feature.account.domain.usecase.GetAccountTypesUseCaseImpl
import com.dimonkiv.savingstracker.feature.account.domain.usecase.GetAccountsUseCase
import com.dimonkiv.savingstracker.feature.account.domain.usecase.GetAccountsUseCaseImpl
import com.dimonkiv.savingstracker.feature.account.presentation.accounts.AccountsReducer
import com.dimonkiv.savingstracker.feature.account.presentation.accounts.AccountsViewModel
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.AddAccountReducer
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.AddAccountViewModel
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.SelectAccountTypeReducer
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.SelectAccountTypeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val accountDataModule = module {
    single { get<AppDatabase>().accountDao() }
    single { get<AppDatabase>().accountTypeDao() }
    single<AccountRepository> {
        AccountRepositoryImpl(accountDao = get())
    }
    single<AccountTypeRepository> {
        AccountTypeRepositoryImpl(dao = get())
    }
}

val accountDomainModule = module {
    factory<GetAccountsUseCase> { GetAccountsUseCaseImpl(get()) }
    factory<CreateAccountUseCase> { CreateAccountUseCaseImpl(get()) }
    factory<GetAccountTypesUseCase> { GetAccountTypesUseCaseImpl(get()) }
}

val accountUiModule = module {
    factory { AccountsReducer() }
    factory { AddAccountReducer() }
    factory { SelectAccountTypeReducer() }
    viewModel { AccountsViewModel(get(), get()) }
    viewModel { AddAccountViewModel(reducer = get(), useCase = get()) }
    viewModel { SelectAccountTypeViewModel(get(), get()) }
}

val accountModules = listOf(accountDataModule, accountDomainModule, accountUiModule)



package com.dimonkiv.savingstracker.account.di

import com.dimonkiv.savingstracker.account.data.repository.AccountRepositoryImpl
import com.dimonkiv.savingstracker.account.data.repository.AccountTypeRepositoryImpl
import com.dimonkiv.savingstracker.account.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.account.domain.repository.AccountTypeRepository
import com.dimonkiv.savingstracker.account.domain.use_cases.CreateAccountUseCase
import com.dimonkiv.savingstracker.account.domain.use_cases.CreateAccountUseCaseImpl
import com.dimonkiv.savingstracker.account.domain.use_cases.GetAccountTypesUseCase
import com.dimonkiv.savingstracker.account.domain.use_cases.GetAccountTypesUseCaseImpl
import com.dimonkiv.savingstracker.account.domain.use_cases.GetAccountsUseCase
import com.dimonkiv.savingstracker.account.domain.use_cases.GetAccountsUseCaseImpl
import com.dimonkiv.savingstracker.account.presentation.accounts.AccountsViewModel
import com.dimonkiv.savingstracker.account.presentation.addaccount.AddAccountViewModel
import com.dimonkiv.savingstracker.account.presentation.addaccount.account_type.SelectAccountTypeViewModel
import com.dimonkiv.savingstracker.core.db.AppDatabase
import com.dimonkiv.savingstracker.core.di.Dispatcher
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val accountDataModule = module {
    single { get<AppDatabase>().accountDao() }
    single { get<AppDatabase>().accountTypeDao() }
    single<AccountRepository> {
        AccountRepositoryImpl(
            accountDao = get(),
            ioDispatcher = get(named(Dispatcher.IO))
        )
    }
    single<AccountTypeRepository> {
        AccountTypeRepositoryImpl(
            dao = get(),
            ioDispatcher = get(named(Dispatcher.IO))
        )
    }
}

val accountDomainModule = module {
    factory<GetAccountsUseCase> { GetAccountsUseCaseImpl(get(), get()) }
    factory<CreateAccountUseCase> { CreateAccountUseCaseImpl(get()) }
    factory<GetAccountTypesUseCase> { GetAccountTypesUseCaseImpl(get()) }
}

val accountUiModule = module {
    viewModel { AccountsViewModel(get()) }
    viewModel { AddAccountViewModel(get()) }
    viewModel { SelectAccountTypeViewModel(get()) }
}

val accountModules = listOf(accountDataModule, accountDomainModule, accountUiModule)



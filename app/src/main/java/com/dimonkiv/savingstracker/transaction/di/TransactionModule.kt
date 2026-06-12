package com.dimonkiv.savingstracker.transaction.di

import com.dimonkiv.savingstracker.transaction.domain.GetAddTransactionUseCase
import com.dimonkiv.savingstracker.transaction.domain.GetAddTransactionUseCaseImpl
import com.dimonkiv.savingstracker.transaction.presentation.AddTransactionReducer
import com.dimonkiv.savingstracker.transaction.presentation.AddTransactionReducerImpl
import com.dimonkiv.savingstracker.transaction.presentation.AddTransactionViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val transactionDomainModule = module {
    factory<GetAddTransactionUseCase> { GetAddTransactionUseCaseImpl(get()) }
}

val transactionUiModule = module {
    factory<AddTransactionReducer> { AddTransactionReducerImpl() }
    viewModel { AddTransactionViewModel(get(), get(), get()) }
}

val transactionModules = listOf(transactionDomainModule, transactionUiModule)
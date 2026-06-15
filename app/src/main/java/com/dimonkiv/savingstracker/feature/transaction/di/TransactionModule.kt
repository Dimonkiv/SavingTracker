package com.dimonkiv.savingstracker.feature.transaction.di

import com.dimonkiv.savingstracker.feature.transaction.domain.GetAddTransactionUseCase
import com.dimonkiv.savingstracker.feature.transaction.domain.GetAddTransactionUseCaseImpl
import com.dimonkiv.savingstracker.feature.transaction.presentation.AddTransactionViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val transactionDomainModule = module {
    factory<GetAddTransactionUseCase> { GetAddTransactionUseCaseImpl(get()) }
}

val transactionUiModule = module {
    viewModel { AddTransactionViewModel(get(), get()) }
}

val transactionModules = listOf(transactionDomainModule, transactionUiModule)
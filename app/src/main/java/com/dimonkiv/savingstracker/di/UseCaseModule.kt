package com.dimonkiv.savingstracker.di

import com.dimonkiv.savingstracker.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.domain.repository.ExpenseRepository
import com.dimonkiv.savingstracker.domain.use_cases.AddExpenseUseCase
import com.dimonkiv.savingstracker.domain.use_cases.AddExpenseUseCaseImpl
import com.dimonkiv.savingstracker.domain.use_cases.GetAccountsUseCase
import com.dimonkiv.savingstracker.domain.use_cases.GetAccountsUseCaseImpl
import com.dimonkiv.savingstracker.domain.use_cases.GetTotalBalanceUseCase
import com.dimonkiv.savingstracker.domain.use_cases.GetTotalBalanceUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideGetTotalBalanceUseCase(
        accountRepository: AccountRepository
    ): GetTotalBalanceUseCase {
        return GetTotalBalanceUseCaseImpl(accountRepository)
    }

    @Provides
    @Singleton
    fun provideGetAccountsUseCase(
        accountRepository: AccountRepository
    ): GetAccountsUseCase {
        return GetAccountsUseCaseImpl(accountRepository)
    }

    @Provides
    @Singleton
    fun provideAddExpenseUseCase(
        expenseRepository: ExpenseRepository,
        accountRepository: AccountRepository
    ): AddExpenseUseCase {
        return AddExpenseUseCaseImpl(expenseRepository, accountRepository)
    }
}
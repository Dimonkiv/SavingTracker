package com.dimonkiv.savingstracker.di

import com.dimonkiv.savingstracker.domain.AccountRepository
import com.dimonkiv.savingstracker.use_case.GetTotalBalanceUseCase
import com.dimonkiv.savingstracker.use_case.GetTotalBalanceUseCaseImpl
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
}
package com.dimonkiv.savingstracker.di

import com.dimonkiv.savingstracker.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.domain.repository.AccountTypeRepository
import com.dimonkiv.savingstracker.domain.use_cases.GetAccountsUseCase
import com.dimonkiv.savingstracker.domain.use_cases.GetSelectedIconUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAccountUseCase(
        accountRepository: AccountRepository,
        typesRepository: AccountTypeRepository
    ): GetAccountsUseCase {
        return GetAccountsUseCase(accountRepository, typesRepository)
    }

    @Provides
    fun provideGetSelectedIconUseCase(
        @Default
        dispatcher: CoroutineDispatcher
    ): GetSelectedIconUseCase {
        return GetSelectedIconUseCase(dispatcher)
    }
}
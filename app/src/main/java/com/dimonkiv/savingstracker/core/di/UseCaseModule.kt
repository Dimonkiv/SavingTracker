package com.dimonkiv.savingstracker.core.di

import com.dimonkiv.savingstracker.account.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.account.domain.repository.AccountTypeRepository
import com.dimonkiv.savingstracker.account.domain.use_cases.GetAccountsUseCase
import com.dimonkiv.savingstracker.select_icon.domain.GetSelectedIconUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

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
    fun provideGetSelectedIconUseCase(): GetSelectedIconUseCase {
        return GetSelectedIconUseCase()
    }
}
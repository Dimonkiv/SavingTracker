package com.dimonkiv.savingstracker.core.di

import com.dimonkiv.savingstracker.account.data.local.dao.AccountDao
import com.dimonkiv.savingstracker.account.data.local.dao.AccountTypeDao
import com.dimonkiv.savingstracker.account.data.repository.AccountDataRepository
import com.dimonkiv.savingstracker.account.data.repository.AccountTypeDataRepository
import com.dimonkiv.savingstracker.account.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.account.domain.repository.AccountTypeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAccountRepository(
        accountDao: AccountDao,
        @IO dispatcher: CoroutineDispatcher
    ): AccountRepository {
        return AccountDataRepository(accountDao, dispatcher)
    }

    @Provides
    @Singleton
    fun provideAccountTypeRepository(
        dao: AccountTypeDao,
        @IO dispatcher: CoroutineDispatcher
    ): AccountTypeRepository {
        return AccountTypeDataRepository(dao, dispatcher)
    }
}
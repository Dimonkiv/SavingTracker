package com.dimonkiv.savingstracker.di

import com.dimonkiv.savingstracker.data.local.dao.AccountDao
import com.dimonkiv.savingstracker.data.local.dao.ExpenseDao
import com.dimonkiv.savingstracker.data.repository.AccountDataRepository
import com.dimonkiv.savingstracker.data.repository.ExpenseDataRepository
import com.dimonkiv.savingstracker.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.domain.repository.ExpenseRepository
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
        dispatcher: CoroutineDispatcher
    ): AccountRepository {
        return AccountDataRepository(accountDao, dispatcher)
    }

    @Provides
    @Singleton
    fun provideExpenseRepository(
        expenseDao: ExpenseDao,
        dispatcher: CoroutineDispatcher
    ): ExpenseRepository {
        return ExpenseDataRepository(expenseDao, dispatcher)
    }
}
package com.dimonkiv.savingstracker.di

import com.dimonkiv.savingstracker.data.AccountRepositoryImpl
import com.dimonkiv.savingstracker.data.ExpenseRepositoryImpl
import com.dimonkiv.savingstracker.domain.AccountRepository
import com.dimonkiv.savingstracker.domain.ExpenseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAccountRepository(): AccountRepository {
        return AccountRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideExpenseRepository(): ExpenseRepository {
        return ExpenseRepositoryImpl()
    }
}
package com.dimonkiv.savingstracker.transaction.di

import com.dimonkiv.savingstracker.transaction.domain.GetAddTransactionUseCase
import com.dimonkiv.savingstracker.transaction.domain.GetAddTransactionUseCaseImpl
import com.dimonkiv.savingstracker.transaction.presentation.AddTransactionReducer
import com.dimonkiv.savingstracker.transaction.presentation.AddTransactionReducerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface TransactionModule {

    @Binds
    fun bindTransactionUseCase(impl: GetAddTransactionUseCaseImpl): GetAddTransactionUseCase

    @Binds
    fun bindAddTransactionReducer(impl: AddTransactionReducerImpl): AddTransactionReducer
}
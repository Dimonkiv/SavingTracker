package com.dimonkiv.savingstracker.core.di

import android.content.Context
import androidx.room.Room
import com.dimonkiv.savingstracker.account.data.local.dao.AccountDao
import com.dimonkiv.savingstracker.core.db.AppDatabase
import com.dimonkiv.savingstracker.account.data.local.dao.AccountTypeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext
        context: Context
    ): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "SavingDB").build()
    }

    @Provides
    @Singleton
    fun provideAccountDao(
        appDatabase: AppDatabase
    ): AccountDao {
        return appDatabase.accountDao()
    }

    @Provides
    @Singleton
    fun provideAccountTypeDao(
        appDatabase: AppDatabase
    ): AccountTypeDao {
        return appDatabase.accountTypeDao()
    }
}
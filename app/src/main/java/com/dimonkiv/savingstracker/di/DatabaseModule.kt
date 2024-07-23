package com.dimonkiv.savingstracker.di

import android.content.Context
import androidx.room.Room
import com.dimonkiv.savingstracker.data.local.dao.AccountDao
import com.dimonkiv.savingstracker.data.local.AppDatabase
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
}
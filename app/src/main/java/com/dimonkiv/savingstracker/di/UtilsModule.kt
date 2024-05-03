package com.dimonkiv.savingstracker.di

import android.content.Context
import com.dimonkiv.savingstracker.core.utils.ResourceManager
import com.dimonkiv.savingstracker.core.utils.ResourceManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    @Provides
    @Singleton
    fun provideResourceManager(
        @ApplicationContext
        context: Context
    ): ResourceManager {
        return ResourceManagerImpl(context)
    }
}
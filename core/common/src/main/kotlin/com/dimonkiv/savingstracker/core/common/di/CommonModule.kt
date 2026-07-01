package com.dimonkiv.savingstracker.core.common.di

import com.dimonkiv.savingstracker.core.utils.ResourceManager
import com.dimonkiv.savingstracker.core.utils.ResourceManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val commonUtilsModule = module {
    single<ResourceManager> { ResourceManagerImpl(androidContext()) }
}

val commonModules = listOf(commonUtilsModule)

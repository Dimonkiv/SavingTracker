package com.dimonkiv.savingstracker.core

import android.app.Application
import com.dimonkiv.savingstracker.core.di.coreModules
import com.dimonkiv.savingstracker.feature.account.di.accountModules
import com.dimonkiv.savingstracker.feature.main.di.mainModule
import com.dimonkiv.savingstracker.feature.select_icon.di.selectIconModules
import com.dimonkiv.savingstracker.feature.transaction.di.transactionModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MainApplication)
            modules(
                coreModules +
                    accountModules +
                    selectIconModules +
                    transactionModules +
                    listOf(mainModule)
            )
        }
    }
}

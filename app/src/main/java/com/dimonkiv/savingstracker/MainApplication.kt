package com.dimonkiv.savingstracker

import android.app.Application
import com.dimonkiv.savingstracker.core.common.di.commonModules
import com.dimonkiv.savingstracker.core.database.di.databaseModules
import com.dimonkiv.savingstracker.core.navigation.di.navigationModule
import com.dimonkiv.savingstracker.feature.account.di.accountModules
import com.dimonkiv.savingstracker.feature.main.di.mainModules
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
                databaseModules +
                    commonModules +
                    accountModules +
                    selectIconModules +
                    transactionModules +
                    mainModules +
                    listOf(navigationModule)
            )
        }
    }
}

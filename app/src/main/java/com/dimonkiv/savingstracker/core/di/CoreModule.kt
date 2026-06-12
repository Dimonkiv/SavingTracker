package com.dimonkiv.savingstracker.core.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dimonkiv.savingstracker.core.db.AppDatabase
import com.dimonkiv.savingstracker.core.utils.ResourceManager
import com.dimonkiv.savingstracker.core.utils.ResourceManagerImpl
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {
    single(named(Dispatcher.IO)) { Dispatchers.IO }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDatabase::class.java,
            name = DATABASE_NAME
        )
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    db.execSQL("INSERT INTO account_type (title, color, is_default) VALUES ('Cash', 'lightGreen', 1)")
                    db.execSQL("INSERT INTO account_type (title, color, is_default) VALUES ('Bank account', 'blue', 1)")
                    db.execSQL("INSERT INTO account_type (title, color, is_default) VALUES ('Invest', 'orange', 1)")
                }
            })
            .build()
    }
}

val utilsModule = module {
    single<ResourceManager> { ResourceManagerImpl(androidContext()) }
}

val coreModules = listOf(dispatcherModule, databaseModule, utilsModule)

private const val DATABASE_NAME = "SavingDB"

enum class Dispatcher {
    IO
}
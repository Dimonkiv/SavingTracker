package com.dimonkiv.savingstracker.core.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dimonkiv.savingstracker.core.db.AppDatabase
import com.dimonkiv.savingstracker.core.utils.ResourceManager
import com.dimonkiv.savingstracker.core.utils.ResourceManagerImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = androidContext(),
            klass = AppDatabase::class.java,
            name = DATABASE_NAME
        )
            .fallbackToDestructiveMigration(dropAllTables = true)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    db.execSQL("INSERT INTO account_type (title, color) VALUES ('Cash', 'lightGreen')")
                    db.execSQL("INSERT INTO account_type (title, color) VALUES ('Bank account', 'blue')")
                    db.execSQL("INSERT INTO account_type (title, color) VALUES ('Invest', 'orange')")
                }
            })
            .build()
    }
}

val utilsModule = module {
    single<ResourceManager> { ResourceManagerImpl(androidContext()) }
}

val coreModules = listOf(databaseModule, utilsModule)

private const val DATABASE_NAME = "SavingDB"
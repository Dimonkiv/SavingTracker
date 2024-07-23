package com.dimonkiv.savingstracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dimonkiv.savingstracker.data.local.dao.AccountDao
import com.dimonkiv.savingstracker.data.local.dto.AccountDTO

@Database(entities = [AccountDTO::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao

}
package com.dimonkiv.savingstracker.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dimonkiv.savingstracker.core.database.dao.AccountDao
import com.dimonkiv.savingstracker.core.database.dao.AccountTypeDao
import com.dimonkiv.savingstracker.core.database.dto.AccountDTO
import com.dimonkiv.savingstracker.core.database.dto.AccountTypeDTO

@Database(entities = [AccountDTO::class, AccountTypeDTO::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    abstract fun accountDao(): AccountDao
    abstract fun accountTypeDao(): AccountTypeDao
}
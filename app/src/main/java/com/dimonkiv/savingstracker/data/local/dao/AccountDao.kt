package com.dimonkiv.savingstracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dimonkiv.savingstracker.data.local.dto.AccountDTO

@Dao
interface AccountDao {
    @Query("SELECT * FROM account")
    suspend fun getAllAccounts(): List<AccountDTO>

    @Query("SELECT * FROM account WHERE id=:id")
    suspend fun getAccountById(id: Long): AccountDTO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAccount(account: AccountDTO)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateAccount(account: AccountDTO)
}
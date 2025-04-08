package com.dimonkiv.savingstracker.account.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dimonkiv.savingstracker.account.data.local.dto.AccountTypeDTO

@Dao
interface AccountTypeDao {
    @Query("SELECT * FROM account_type")
    suspend fun getAllTypes(): List<AccountTypeDTO>

    @Query("SELECT * FROM account_type WHERE id=:id")
    suspend fun getTypeById(id: Long): AccountTypeDTO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertType(type: AccountTypeDTO)
}
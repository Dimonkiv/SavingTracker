package com.dimonkiv.savingstracker.feature.account.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.dimonkiv.savingstracker.feature.account.data.local.dto.AccountTypeDTO
import com.dimonkiv.savingstracker.feature.account.data.local.dto.AccountTypeWithAccountsDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountTypeDao {
    @Query("SELECT * FROM account_type")
    suspend fun getAllTypes(): List<AccountTypeDTO>

    @Query("SELECT * FROM account_type WHERE id=:id")
    suspend fun getTypeById(id: Long): AccountTypeDTO

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertType(type: AccountTypeDTO)

    @Transaction
    @Query("SELECT * FROM account_type")
    fun getTypesWithAccounts(): Flow<List<AccountTypeWithAccountsDTO>>
}
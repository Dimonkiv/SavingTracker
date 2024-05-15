package com.dimonkiv.savingstracker.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dimonkiv.savingstracker.data.local.dto.ExpenseDTO

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM expense")
    suspend fun getAllExpenses(): List<ExpenseDTO>

    @Query("SELECT * FROM expense WHERE id=:id")
    suspend fun getExpenseById(id: Long): List<ExpenseDTO>

    @Query("SELECT * FROM expense WHERE account_id=:accountId")
    suspend fun getExpenseByAccountId(accountId: Long): List<ExpenseDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: ExpenseDTO)

    @Update
    suspend fun updateExpense(expense: ExpenseDTO)
}
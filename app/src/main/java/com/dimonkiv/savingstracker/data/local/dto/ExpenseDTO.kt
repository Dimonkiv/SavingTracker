package com.dimonkiv.savingstracker.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dimonkiv.savingstracker.domain.model.Expense
import com.dimonkiv.savingstracker.presentation.utils.DateUtils

@Entity(tableName = "expense")
data class ExpenseDTO(
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "value")
    val value: Int,
    @ColumnInfo(name = "date")
    val date: Long,
    @ColumnInfo(name = "account_id")
    val accountId: Long,
    @ColumnInfo(name = "is_expense")
    val isExpense: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

fun ExpenseDTO.asDomain() = Expense(
    title = title,
    value = value,
    date = DateUtils.parseDate(date),
    accountId = accountId,
    isExpense = isExpense
)

fun Expense.asDTO() = ExpenseDTO(
    title = title,
    value = value,
    date = DateUtils.parseDateToMillis(date),
    accountId = accountId,
    isExpense = isExpense
)

fun List<ExpenseDTO>.asDomain(): List<Expense> {
    return map { it.asDomain() }
}

fun List<Expense>.asDTO(): List<ExpenseDTO> {
    return map { it.asDTO() }
}

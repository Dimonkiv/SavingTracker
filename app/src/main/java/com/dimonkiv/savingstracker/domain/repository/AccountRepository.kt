package com.dimonkiv.savingstracker.domain.repository

import com.dimonkiv.savingstracker.domain.model.Account

interface AccountRepository {

    suspend fun fetchAccounts(): List<Account>

    suspend fun fetchAccountById(accountId: Long): Account

    suspend fun addAccount(account: Account)

    suspend fun updateAccount(account: Account)

    suspend fun deleteAccount(id: Long)
}
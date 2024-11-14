package com.dimonkiv.savingstracker.domain.repository

import com.dimonkiv.savingstracker.domain.model.Account

interface AccountRepository {

    suspend fun createAccount(account: Account)

    suspend fun fetchAccounts(): List<Account>

    suspend fun fetchAccountById(accountId: Long): Account

    suspend fun deleteAccount(id: Long)
}
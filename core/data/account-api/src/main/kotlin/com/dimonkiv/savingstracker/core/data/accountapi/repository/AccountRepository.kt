package com.dimonkiv.savingstracker.core.data.accountapi.repository

import com.dimonkiv.savingstracker.core.data.accountapi.model.Account

interface AccountRepository {

    suspend fun createAccount(account: Account)

    suspend fun fetchAccounts(): List<Account>

    suspend fun fetchAccountById(accountId: Long): Account?

    suspend fun deleteAccount(id: Long)
}
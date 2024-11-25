package com.dimonkiv.savingstracker.account.domain.repository

import com.dimonkiv.savingstracker.account.domain.model.Account

interface AccountRepository {

    suspend fun createAccount(account: Account)

    suspend fun fetchAccounts(): List<Account>

    suspend fun fetchAccountById(accountId: Long): Account

    suspend fun deleteAccount(id: Long)
}
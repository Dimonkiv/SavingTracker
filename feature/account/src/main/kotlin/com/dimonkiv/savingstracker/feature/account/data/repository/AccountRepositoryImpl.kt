package com.dimonkiv.savingstracker.feature.account.data.repository

import com.dimonkiv.savingstracker.core.database.dao.AccountDao
import com.dimonkiv.savingstracker.core.database.dto.asDTO
import com.dimonkiv.savingstracker.core.database.dto.asDomain
import com.dimonkiv.savingstracker.core.data.accountapi.model.Account
import com.dimonkiv.savingstracker.core.data.accountapi.repository.AccountRepository

class AccountRepositoryImpl(
    private val accountDao: AccountDao
): AccountRepository {

    override suspend fun createAccount(account: Account) =
        accountDao.insertAccount(account.asDTO())

    override suspend fun fetchAccounts(): List<Account> =
        accountDao.getAllAccounts().asDomain()

    override suspend fun fetchAccountById(accountId: Long): Account? =
        runCatching { accountDao.getAccountById(accountId).asDomain() }.getOrNull()

    override suspend fun deleteAccount(id: Long) =
        accountDao.deleteAccount(id)
}
package com.dimonkiv.savingstracker.feature.account.data.repository

import com.dimonkiv.savingstracker.feature.account.data.local.dao.AccountDao
import com.dimonkiv.savingstracker.feature.account.data.local.dto.asDTO
import com.dimonkiv.savingstracker.feature.account.data.local.dto.asDomain
import com.dimonkiv.savingstracker.feature.account.domain.model.Account
import com.dimonkiv.savingstracker.feature.account.domain.repository.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AccountRepositoryImpl(
    private val accountDao: AccountDao,
    private val ioDispatcher: CoroutineDispatcher
): AccountRepository {

    override suspend fun createAccount(account: Account) = withContext(ioDispatcher) {
        accountDao.insertAccount(account.asDTO())
    }

    override suspend fun fetchAccounts(): List<Account> = withContext(ioDispatcher) {
        accountDao.getAllAccounts().asDomain()
    }

    override suspend fun fetchAccountById(accountId: Long): Account = withContext(ioDispatcher) {
        accountDao.getAccountById(accountId).asDomain()
    }

    override suspend fun deleteAccount(id: Long) = withContext(ioDispatcher) {
        accountDao.deleteAccount(id)
    }
}
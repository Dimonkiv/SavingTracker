package com.dimonkiv.savingstracker.data.repository

import com.dimonkiv.savingstracker.data.local.dao.AccountDao
import com.dimonkiv.savingstracker.data.local.dto.asDomain
import com.dimonkiv.savingstracker.di.IO
import com.dimonkiv.savingstracker.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.domain.model.Account
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountDataRepository @Inject constructor(
    private val accountDao: AccountDao,
    @IO
    private val dispatcher: CoroutineDispatcher
): AccountRepository {

    override suspend fun fetchAccounts(): List<Account> = withContext(dispatcher) {
        accountDao.getAllAccounts().asDomain()
    }

    override suspend fun fetchAccountById(accountId: Long): Account = withContext(dispatcher) {
        accountDao.getAccountById(accountId).asDomain()
    }

    override suspend fun deleteAccount(id: Long) = withContext(dispatcher) {
        accountDao.deleteAccount(id)
    }
}
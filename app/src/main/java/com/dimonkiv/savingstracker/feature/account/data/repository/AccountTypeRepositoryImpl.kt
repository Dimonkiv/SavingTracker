package com.dimonkiv.savingstracker.feature.account.data.repository

import com.dimonkiv.savingstracker.feature.account.data.local.dao.AccountTypeDao
import com.dimonkiv.savingstracker.feature.account.domain.repository.AccountTypeRepository
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.AccountTypeModel
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.asDTO
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.asPresentation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AccountTypeRepositoryImpl(
    private val dao: AccountTypeDao,
    private val ioDispatcher: CoroutineDispatcher
): AccountTypeRepository {
    override suspend fun createAccountType(type: AccountTypeModel) = withContext(ioDispatcher) {
        dao.insertType(type.asDTO())
    }

    override suspend fun fetchAccountTypes() = withContext(ioDispatcher) {
        dao.getAllTypes().asPresentation()
    }

    override suspend fun fetchAccountTypeById(id: Long) = withContext(ioDispatcher) {
        dao.getTypeById(id).asPresentation()
    }
}
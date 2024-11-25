package com.dimonkiv.savingstracker.account.data.repository

import com.dimonkiv.savingstracker.account.data.local.dao.AccountTypeDao
import com.dimonkiv.savingstracker.core.di.IO
import com.dimonkiv.savingstracker.account.domain.repository.AccountTypeRepository
import com.dimonkiv.savingstracker.account.presentation.add_account.account_type.model.AccountTypeModel
import com.dimonkiv.savingstracker.account.presentation.add_account.account_type.model.asDTO
import com.dimonkiv.savingstracker.account.presentation.add_account.account_type.model.asPresentation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccountTypeDataRepository@Inject constructor(
    private val dao: AccountTypeDao,
    @IO
    private val dispatcher: CoroutineDispatcher
): AccountTypeRepository {
    override suspend fun createAccountType(type: AccountTypeModel) = withContext(dispatcher) {
        dao.insertType(type.asDTO())
    }

    override suspend fun fetchAccountTypes() = withContext(dispatcher) {
        dao.getAllTypes().asPresentation()
    }

    override suspend fun fetchAccountTypeById(id: Long) = withContext(dispatcher) {
        dao.getTypeById(id).asPresentation()
    }
}
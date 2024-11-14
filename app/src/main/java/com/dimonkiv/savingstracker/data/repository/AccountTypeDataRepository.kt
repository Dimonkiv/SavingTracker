package com.dimonkiv.savingstracker.data.repository

import com.dimonkiv.savingstracker.data.local.dao.AccountTypeDao
import com.dimonkiv.savingstracker.di.IO
import com.dimonkiv.savingstracker.domain.repository.AccountTypeRepository
import com.dimonkiv.savingstracker.presentation.account.add_account.account_type.model.AccountTypeModel
import com.dimonkiv.savingstracker.presentation.account.add_account.account_type.model.asDTO
import com.dimonkiv.savingstracker.presentation.account.add_account.account_type.model.asPresentation
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
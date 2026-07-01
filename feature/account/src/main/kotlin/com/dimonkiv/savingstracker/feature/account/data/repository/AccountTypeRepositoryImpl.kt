package com.dimonkiv.savingstracker.feature.account.data.repository

import com.dimonkiv.savingstracker.core.database.dao.AccountTypeDao
import com.dimonkiv.savingstracker.core.database.dto.asDomain
import com.dimonkiv.savingstracker.core.database.dto.asDto
import com.dimonkiv.savingstracker.core.data.accountapi.model.AccountType
import com.dimonkiv.savingstracker.core.data.accountapi.model.AccountTypeWithAccounts
import com.dimonkiv.savingstracker.core.data.accountapi.repository.AccountTypeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class AccountTypeRepositoryImpl(
    private val dao: AccountTypeDao
): AccountTypeRepository {
    override suspend fun createAccountType(type: AccountType) =
        dao.insertType(type.asDto())

    override suspend fun fetchAccountTypes() =
        dao.getAllTypes().asDomain()

    override suspend fun fetchAccountTypeById(id: Long) =
        dao.getTypeById(id).asDomain()

    override fun getTypesWithAccounts(): Flow<List<AccountTypeWithAccounts>> =
        dao.getTypesWithAccounts()
            .map { it.asDomain() }
            .catch { emit(emptyList()) }
}
package com.dimonkiv.savingstracker.core.data.accountapi.repository

import com.dimonkiv.savingstracker.core.data.accountapi.model.AccountType
import com.dimonkiv.savingstracker.core.data.accountapi.model.AccountTypeWithAccounts
import kotlinx.coroutines.flow.Flow

interface AccountTypeRepository {
    suspend fun createAccountType(type: AccountType)

    suspend fun fetchAccountTypes(): List<AccountType>

    suspend fun fetchAccountTypeById(id: Long): AccountType

    fun getTypesWithAccounts(): Flow<List<AccountTypeWithAccounts>>
}
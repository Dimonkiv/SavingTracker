package com.dimonkiv.savingstracker.feature.account.domain.repository

import com.dimonkiv.savingstracker.feature.account.domain.model.AccountType
import com.dimonkiv.savingstracker.feature.account.domain.model.AccountTypeWithAccounts
import kotlinx.coroutines.flow.Flow

interface AccountTypeRepository {
    suspend fun createAccountType(type: AccountType)

    suspend fun fetchAccountTypes(): List<AccountType>

    suspend fun fetchAccountTypeById(id: Long): AccountType

    fun getTypesWithAccounts(): Flow<List<AccountTypeWithAccounts>>
}
package com.dimonkiv.savingstracker.domain.repository

import com.dimonkiv.savingstracker.presentation.account.add_account.account_type.model.AccountTypeModel

interface AccountTypeRepository {
    suspend fun createAccountType(type: AccountTypeModel)

    suspend fun fetchAccountTypes(): List<AccountTypeModel>

    suspend fun fetchAccountTypeById(id: Long): AccountTypeModel
}
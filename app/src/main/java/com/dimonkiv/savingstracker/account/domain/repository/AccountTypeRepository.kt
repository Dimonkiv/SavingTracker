package com.dimonkiv.savingstracker.account.domain.repository

import com.dimonkiv.savingstracker.account.presentation.add_account.account_type.model.AccountTypeModel

interface AccountTypeRepository {
    suspend fun createAccountType(type: AccountTypeModel)

    suspend fun fetchAccountTypes(): List<AccountTypeModel>

    suspend fun fetchAccountTypeById(id: Long): AccountTypeModel
}
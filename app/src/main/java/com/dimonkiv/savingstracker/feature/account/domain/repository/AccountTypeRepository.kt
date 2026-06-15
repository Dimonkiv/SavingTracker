package com.dimonkiv.savingstracker.feature.account.domain.repository

import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.AccountTypeModel

interface AccountTypeRepository {
    suspend fun createAccountType(type: AccountTypeModel)

    suspend fun fetchAccountTypes(): List<AccountTypeModel>

    suspend fun fetchAccountTypeById(id: Long): AccountTypeModel
}
package com.dimonkiv.savingstracker.domain.use_cases

import com.dimonkiv.savingstracker.domain.model.AccountType

interface GetAccountTypeUseCase {
    fun invoke(type: AccountType.Type): AccountType
}
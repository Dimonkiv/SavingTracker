package com.dimonkiv.savingstracker.domain.use_cases

import com.dimonkiv.savingstracker.presentation.add_account.AccountType

interface GetAccountTypeUseCase {
    fun invoke(type: AccountType.Type): AccountType
}
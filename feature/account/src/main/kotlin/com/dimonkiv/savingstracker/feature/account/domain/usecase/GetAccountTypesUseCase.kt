package com.dimonkiv.savingstracker.feature.account.domain.usecase

import com.dimonkiv.savingstracker.core.data.accountapi.model.AccountType
import com.dimonkiv.savingstracker.core.data.accountapi.repository.AccountTypeRepository

interface GetAccountTypesUseCase {
    suspend fun invoke(): List<AccountType>
}

class GetAccountTypesUseCaseImpl(
    private val accountTypeRepository: AccountTypeRepository
) : GetAccountTypesUseCase {
    override suspend fun invoke() = accountTypeRepository.fetchAccountTypes()
}

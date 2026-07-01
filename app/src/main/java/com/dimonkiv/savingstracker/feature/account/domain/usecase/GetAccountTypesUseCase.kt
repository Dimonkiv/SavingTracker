package com.dimonkiv.savingstracker.feature.account.domain.usecase

import com.dimonkiv.savingstracker.feature.account.domain.model.AccountType
import com.dimonkiv.savingstracker.feature.account.domain.repository.AccountTypeRepository

interface GetAccountTypesUseCase {
    suspend fun invoke(): List<AccountType>
}

class GetAccountTypesUseCaseImpl(
    private val accountTypeRepository: AccountTypeRepository
) : GetAccountTypesUseCase {
    override suspend fun invoke() = accountTypeRepository.fetchAccountTypes()
}

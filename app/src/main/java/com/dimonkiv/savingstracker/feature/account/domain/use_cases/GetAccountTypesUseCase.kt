package com.dimonkiv.savingstracker.feature.account.domain.use_cases

import com.dimonkiv.savingstracker.feature.account.domain.repository.AccountTypeRepository
import com.dimonkiv.savingstracker.feature.account.presentation.addaccount.account_type.model.AccountTypeModel

interface GetAccountTypesUseCase {
    suspend fun invoke(): List<AccountTypeModel>
}

class GetAccountTypesUseCaseImpl(
    private val accountTypeRepository: AccountTypeRepository
) : GetAccountTypesUseCase {
    override suspend fun invoke() = accountTypeRepository.fetchAccountTypes()
}

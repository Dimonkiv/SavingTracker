package com.dimonkiv.savingstracker.feature.account.domain.usecase

import com.dimonkiv.savingstracker.core.data.accountapi.model.Account
import com.dimonkiv.savingstracker.core.data.accountapi.repository.AccountRepository

interface CreateAccountUseCase {
    suspend fun invoke(account: Account)
}

class CreateAccountUseCaseImpl(
    private val accountRepository: AccountRepository
) : CreateAccountUseCase {
    override suspend fun invoke(account: Account) {
        accountRepository.createAccount(account)
    }
}

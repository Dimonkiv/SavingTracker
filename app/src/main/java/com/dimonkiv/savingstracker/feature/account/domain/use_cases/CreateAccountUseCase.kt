package com.dimonkiv.savingstracker.feature.account.domain.use_cases

import com.dimonkiv.savingstracker.feature.account.domain.model.Account
import com.dimonkiv.savingstracker.feature.account.domain.repository.AccountRepository

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

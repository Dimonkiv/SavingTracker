package com.dimonkiv.savingstracker.account.domain.use_cases

import com.dimonkiv.savingstracker.account.domain.model.Account
import com.dimonkiv.savingstracker.account.domain.repository.AccountRepository

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

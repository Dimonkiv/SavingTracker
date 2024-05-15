package com.dimonkiv.savingstracker.domain.use_cases

import com.dimonkiv.savingstracker.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.domain.model.Account
import com.dimonkiv.savingstracker.domain.model.AccountType
import javax.inject.Inject

class GetAccountsUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository
): GetAccountsUseCase {
    override suspend fun invoke(): List<Account> {
        return accountRepository.fetchAccounts().toMutableList().apply {
            this.add(Account(type = AccountType.DEFAULT))
        }
    }
}
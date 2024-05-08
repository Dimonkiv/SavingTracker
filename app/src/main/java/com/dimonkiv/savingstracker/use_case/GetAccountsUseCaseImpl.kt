package com.dimonkiv.savingstracker.use_case

import com.dimonkiv.savingstracker.domain.AccountRepository
import com.dimonkiv.savingstracker.domain.model.Account
import com.dimonkiv.savingstracker.domain.model.AccountType
import javax.inject.Inject

class GetAccountsUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository
): GetAccountsUseCase {
    override fun execute(): List<Account> {
        val accounts = accountRepository.fetchAccounts()
            .map {
                it.type = AccountType.CREATED
                it
            }.toMutableList()

        accounts.add(Account(type = AccountType.DEFAULT))

        return accounts
    }
}
package com.dimonkiv.savingstracker.domain.use_cases

import com.dimonkiv.savingstracker.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.domain.model.Account
import com.dimonkiv.savingstracker.presentation.add_account.AccountType
import javax.inject.Inject

class GetAccountsUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository,
    private val getAccountTypeUseCase: GetAccountTypeUseCase
): GetAccountsUseCase {
    override suspend fun invoke(): List<Account> {
        val accounts = accountRepository.fetchAccounts().toMutableList()

        for (it in accounts) {
            it.type = getAccountTypeUseCase.invoke(it.type.type)
        }
        val emptyAccount = Account(type = AccountType(type = AccountType.Type.DEFAULT))
        accounts.add(emptyAccount)

        return accounts
    }
}
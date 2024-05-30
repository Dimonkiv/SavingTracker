package com.dimonkiv.savingstracker.domain.use_cases

import com.dimonkiv.savingstracker.domain.model.Account
import com.dimonkiv.savingstracker.domain.repository.AccountRepository
import javax.inject.Inject

class RemoveAccountUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val getAccountsUseCase: GetAccountsUseCase
) {

    suspend fun invoke(id: Long): List<Account> {
        accountRepository.deleteAccount(id)
        return getAccountsUseCase.invoke()
    }
}
package com.dimonkiv.savingstracker.domain.use_cases

import com.dimonkiv.savingstracker.domain.repository.AccountRepository
import javax.inject.Inject

class GetTotalBalanceUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository
) : GetTotalBalanceUseCase {

    override suspend fun invoke(): String {
        var totalBalance = 0
        val items = accountRepository.fetchAccounts()

        for (it in items) {
            totalBalance += it.balance
        }

        return totalBalance.toString()
    }
}
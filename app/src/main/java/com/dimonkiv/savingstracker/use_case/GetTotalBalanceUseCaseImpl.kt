package com.dimonkiv.savingstracker.use_case

import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.core.utils.ResourceManager
import com.dimonkiv.savingstracker.domain.AccountRepository
import javax.inject.Inject

class GetTotalBalanceUseCaseImpl @Inject constructor(
    private val accountRepository: AccountRepository,
    private val resourceManager: ResourceManager
) : GetTotalBalanceUseCase{

    override fun execute(): String {
        var totalBalance = 0
        val items = accountRepository.fetchAccounts()

        for (it in items) {
            totalBalance += it.balance
        }

        return resourceManager.getString(R.string.total_balance, totalBalance)
    }
}
package com.dimonkiv.savingstracker.domain.use_cases

import com.dimonkiv.savingstracker.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.presentation.accounts.model.AccountsModel
import com.dimonkiv.savingstracker.presentation.accounts.model.asPresentation
import javax.inject.Inject

class GetAccountUseCase @Inject constructor(
    private val repository: AccountRepository
) {

    suspend fun invoke(): AccountsModel {
        val accounts = repository.fetchAccounts()
        val totalBalance = accounts.sumOf { it.balance }

        return AccountsModel(
            totalBalance = "$$totalBalance",
            accounts = accounts.asPresentation()
        )
    }
}
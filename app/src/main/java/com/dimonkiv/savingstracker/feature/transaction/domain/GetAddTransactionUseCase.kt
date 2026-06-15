package com.dimonkiv.savingstracker.feature.transaction.domain

import com.dimonkiv.savingstracker.feature.account.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.AddTransactionType

interface GetAddTransactionUseCase {
    suspend fun invoke(): AddTransactionModel
}

class GetAddTransactionUseCaseImpl(
    private val accountRepository: AccountRepository
): GetAddTransactionUseCase {

    override suspend fun invoke(): AddTransactionModel {
        val accounts = accountRepository.fetchAccounts()

        return AddTransactionModel(
            types = listOf(
                AddTransactionType.EXPENSE,
                AddTransactionType.INCOME,
                AddTransactionType.TRANSFER
            ),
            accounts = accounts
        )
    }

}
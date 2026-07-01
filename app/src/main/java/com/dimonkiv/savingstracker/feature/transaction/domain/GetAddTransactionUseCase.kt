package com.dimonkiv.savingstracker.feature.transaction.domain

import com.dimonkiv.savingstracker.feature.account.domain.repository.AccountRepository

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
                TransactionType.EXPENSE,
                TransactionType.INCOME,
                TransactionType.TRANSFER
            ),
            accounts = accounts
        )
    }
}
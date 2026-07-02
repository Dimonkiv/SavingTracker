package com.dimonkiv.savingstracker.feature.account.domain.usecase

import com.dimonkiv.savingstracker.core.data.accountapi.model.AccountsResult
import com.dimonkiv.savingstracker.core.data.accountapi.repository.AccountTypeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface GetAccountsUseCase {
    fun invoke(): Flow<AccountsResult>
}

class GetAccountsUseCaseImpl(
    private val typesRepository: AccountTypeRepository
) : GetAccountsUseCase {

    override fun invoke(): Flow<AccountsResult> {
        return typesRepository.getTypesWithAccounts()
            .map { typesWithAccounts ->
                val filtered = typesWithAccounts.filter { it.accounts.isNotEmpty() }
                val totalBalance = filtered.sumOf { type ->
                    type.accounts.sumOf { it.balance }
                }

                AccountsResult(
                    totalBalance = "$$totalBalance",
                    types = filtered
                )
            }
    }
}
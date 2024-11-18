package com.dimonkiv.savingstracker.domain.use_cases

import com.dimonkiv.savingstracker.domain.repository.AccountRepository
import com.dimonkiv.savingstracker.domain.repository.AccountTypeRepository
import com.dimonkiv.savingstracker.presentation.account.accounts.model.AccountModel
import com.dimonkiv.savingstracker.presentation.account.accounts.model.AccountsModel
import com.dimonkiv.savingstracker.presentation.account.accounts.model.TypesModel
import com.dimonkiv.savingstracker.presentation.account.accounts.model.asPresentation
import javax.inject.Inject

class GetAccountsUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
    private val typesRepository: AccountTypeRepository
) {

    suspend fun invoke(): AccountsModel {
        val accounts = accountRepository.fetchAccounts()
        val totalBalance = accounts.sumOf { it.balance }

        return AccountsModel(
            totalBalance = "$$totalBalance",
            types = filterAccountsByType(accounts.asPresentation())
        )
    }

    private suspend fun filterAccountsByType(accounts: List<AccountModel>): List<TypesModel> {
        val items = mutableListOf<TypesModel>()

        val types = typesRepository.fetchAccountTypes()

        for (type in types) {
            val filteredAccounts = accounts.filter { it.typeId == type.id }

            if (filteredAccounts.isNotEmpty()) {
                val item = TypesModel(
                    id = type.id,
                    title = type.title,
                    color = type.color,
                    accounts = filteredAccounts
                )
                items.add(item)
            }
        }

        return items
    }
}
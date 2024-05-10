package com.dimonkiv.savingstracker.data

import com.dimonkiv.savingstracker.domain.AccountRepository
import com.dimonkiv.savingstracker.domain.model.Account

class AccountRepositoryImpl: AccountRepository {

    private val items = arrayListOf<Account>()
    override fun fetchAccounts(): List<Account> {
        return items
    }

    override fun addAccount(account: Account) {
        items.add(account)
    }


}
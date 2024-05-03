package com.dimonkiv.savingstracker.data

import com.dimonkiv.savingstracker.domain.AccountRepository
import com.dimonkiv.savingstracker.domain.model.Account

class AccountRepositoryImpl: AccountRepository {
    override fun fetchAccounts(): List<Account> {
        val items = arrayListOf<Account>()

        items.add(Account(1, "Account 1", 4200, ))
        items.add(Account(2, "Account 2", 1000))
        items.add(Account(3, "Account 3", 3500))

        return items
    }


}
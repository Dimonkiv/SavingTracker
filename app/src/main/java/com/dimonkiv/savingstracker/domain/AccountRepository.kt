package com.dimonkiv.savingstracker.domain

import com.dimonkiv.savingstracker.domain.model.Account

interface AccountRepository {

    fun fetchAccounts(): List<Account>

    fun addAccount(account: Account)
}
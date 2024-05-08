package com.dimonkiv.savingstracker.use_case

import com.dimonkiv.savingstracker.domain.model.Account

fun interface GetAccountsUseCase {
    fun execute(): List<Account>
}
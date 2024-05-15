package com.dimonkiv.savingstracker.domain.use_cases

import com.dimonkiv.savingstracker.domain.model.Account

fun interface GetAccountsUseCase {
    suspend fun invoke(): List<Account>
}
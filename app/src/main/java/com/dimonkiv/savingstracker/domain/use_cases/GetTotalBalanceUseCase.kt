package com.dimonkiv.savingstracker.domain.use_cases

interface GetTotalBalanceUseCase {

    suspend fun invoke(): String
}
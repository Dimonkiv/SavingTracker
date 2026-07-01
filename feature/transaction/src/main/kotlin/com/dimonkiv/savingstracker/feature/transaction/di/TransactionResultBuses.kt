package com.dimonkiv.savingstracker.feature.transaction.di

import com.dimonkiv.savingstracker.core.navigation.NavResult
import com.dimonkiv.savingstracker.feature.transaction.presentation.model.TransactionAccountModel

class TransactionAccountResultBus : NavResult<TransactionAccountModel>()

class TransactionDateResultBus : NavResult<Long>()

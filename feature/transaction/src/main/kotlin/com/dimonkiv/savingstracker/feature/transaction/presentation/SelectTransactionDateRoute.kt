package com.dimonkiv.savingstracker.feature.transaction.presentation

import androidx.compose.runtime.Composable
import com.dimonkiv.savingstracker.core.navigation.Navigator
import com.dimonkiv.savingstracker.core.navigation.routes.SelectTransactionDate
import com.dimonkiv.savingstracker.designsystem.SelectDateContent
import com.dimonkiv.savingstracker.feature.transaction.di.TransactionDateResultBus
import org.koin.compose.koinInject

@Composable
fun SelectTransactionDateRoute(
    key: SelectTransactionDate,
    navigator: Navigator,
    dateResult: TransactionDateResultBus = koinInject()
) {
    SelectDateContent(
        initialDateMillis = key.initialDateMillis,
        onDateSelected = { millis ->
            dateResult.send(millis)
            navigator.goBack()
        },
        onCancel = { navigator.goBack() }
    )
}

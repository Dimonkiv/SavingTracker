package com.dimonkiv.savingstracker.domain.use_cases

import com.dimonkiv.savingstracker.R
import com.dimonkiv.savingstracker.domain.model.AccountType

class GetAccountTypeUseCaseImpl : GetAccountTypeUseCase {
    override fun invoke(type: AccountType.Type): AccountType {
        return when (type) {
            AccountType.Type.BANK -> AccountType(
                titleRes = R.string.bank_account,
                iconResId = R.drawable.ic_bank_card,
                colorRes = R.color.dark,
                type = type
            )

            AccountType.Type.CASH -> AccountType(
                titleRes = R.string.cash,
                iconResId = R.drawable.ic_cash,
                colorRes = R.color.green,
                type = type
            )

            else -> AccountType(
                titleRes = R.string.investment,
                iconResId = R.drawable.ic_invest,
                colorRes = R.color.blue,
                type = type
            )

        }
    }
}
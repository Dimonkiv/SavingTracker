package com.dimonkiv.savingstracker.domain.model

import com.dimonkiv.savingstracker.R

data class AccountType(
    var titleRes: Int = R.string.bank_account,
    var iconResId: Int = R.drawable.ic_bank_card,
    var colorRes: Int = R.color.dark,
    var type: Type = Type.BANK
) {
    enum class Type {
        BANK, CASH, INVEST, DEFAULT
    }

    companion object {
        fun bank() = AccountType(
            titleRes = R.string.bank_account,
            iconResId = R.drawable.ic_bank_card,
            colorRes = R.color.dark,
            type = Type.BANK
        )

        fun cash() = AccountType(
            titleRes = R.string.cash,
            iconResId = R.drawable.ic_cash,
            colorRes = R.color.green,
            type = Type.CASH
        )

        fun investment() = AccountType(
            titleRes = R.string.investment,
            iconResId = R.drawable.ic_invest,
            colorRes = R.color.blue,
            type = Type.INVEST
        )
    }
}

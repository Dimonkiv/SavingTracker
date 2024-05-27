package com.dimonkiv.savingstracker.presentation.add_account

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
}

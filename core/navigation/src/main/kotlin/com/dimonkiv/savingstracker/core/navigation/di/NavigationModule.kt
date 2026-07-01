package com.dimonkiv.savingstracker.core.navigation.di

import com.dimonkiv.savingstracker.core.navigation.Navigator
import com.dimonkiv.savingstracker.core.navigation.routes.IconColorResultBus
import org.koin.dsl.module

val navigationModule = module {
    single { Navigator() }
    single { IconColorResultBus() }
}

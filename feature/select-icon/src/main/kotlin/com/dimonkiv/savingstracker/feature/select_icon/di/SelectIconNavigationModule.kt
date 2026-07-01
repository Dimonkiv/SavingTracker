package com.dimonkiv.savingstracker.feature.select_icon.di

import com.dimonkiv.savingstracker.core.navigation.routes.SelectIcon
import com.dimonkiv.savingstracker.feature.select_icon.presentation.SelectIconRoute
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val selectIconNavigationModule = module {
    navigation<SelectIcon> { SelectIconRoute(navigator = get()) }
}

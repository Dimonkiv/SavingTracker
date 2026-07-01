package com.dimonkiv.savingstracker.feature.select_icon.di

import com.dimonkiv.savingstracker.feature.select_icon.presentation.SelectIconReducer
import com.dimonkiv.savingstracker.feature.select_icon.presentation.SelectIconViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val selectIconUiModule = module {
    factory { SelectIconReducer() }
    viewModel { SelectIconViewModel(get()) }
}

val selectIconModules = listOf(selectIconUiModule, selectIconNavigationModule)
package com.dimonkiv.savingstracker.feature.select_icon.di

import com.dimonkiv.savingstracker.feature.select_icon.domain.GetSelectedIconUseCase
import com.dimonkiv.savingstracker.feature.select_icon.presentation.SelectIconViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel

val selectIconDomainModule = module {
    factory { GetSelectedIconUseCase() }
}

val selectIconUiModule = module {
    viewModel { SelectIconViewModel(get()) }
}

val selectIconModules = listOf(selectIconDomainModule, selectIconUiModule)
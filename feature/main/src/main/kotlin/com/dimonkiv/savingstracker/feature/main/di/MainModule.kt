package com.dimonkiv.savingstracker.feature.main.di

import com.dimonkiv.savingstracker.feature.main.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel() }
}

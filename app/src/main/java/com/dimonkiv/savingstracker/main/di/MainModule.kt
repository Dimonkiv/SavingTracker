package com.dimonkiv.savingstracker.main.di

import com.dimonkiv.savingstracker.main.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel() }
}

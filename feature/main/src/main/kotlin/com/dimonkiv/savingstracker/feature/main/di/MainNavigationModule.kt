package com.dimonkiv.savingstracker.feature.main.di

import com.dimonkiv.savingstracker.core.navigation.routes.Home
import com.dimonkiv.savingstracker.core.navigation.routes.Profile
import com.dimonkiv.savingstracker.core.navigation.routes.Statistics
import com.dimonkiv.savingstracker.feature.home.HomeScreen
import com.dimonkiv.savingstracker.feature.profile.ProfileScreen
import com.dimonkiv.savingstracker.feature.statistics.StatisticsScreen
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.dsl.module
import org.koin.dsl.navigation3.navigation

@OptIn(KoinExperimentalAPI::class)
val mainNavigationModule = module {
    navigation<Home> { HomeScreen() }
    navigation<Statistics> { StatisticsScreen() }
    navigation<Profile> { ProfileScreen() }
}

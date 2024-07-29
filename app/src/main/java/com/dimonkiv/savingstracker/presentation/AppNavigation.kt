package com.dimonkiv.savingstracker.presentation

enum class Screen {
    MAIN, HOME, ACCOUNTS, STATISTICS, PROFILE, ADD,
    SELECT_ICON
}

sealed class NavigationItem(val route: String) {
    data object Main: NavigationItem(Screen.MAIN.name)
    data object Home: NavigationItem(Screen.HOME.name)
    data object Accounts: NavigationItem(Screen.ACCOUNTS.name)
    data object Statistics: NavigationItem(Screen.STATISTICS.name)
    data object Profile: NavigationItem(Screen.PROFILE.name)
    data object Add: NavigationItem(Screen.ADD.name)
    data object SelectIcon: NavigationItem(Screen.SELECT_ICON.name)
}
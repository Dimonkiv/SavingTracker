package com.dimonkiv.savingstracker.transaction

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.dimonkiv.savingstracker.core.compose.AppToolbar
import com.dimonkiv.savingstracker.core.compose.BaseScreen

@Composable
fun AddTransactionRoute(
    navController: NavHostController
) {

    BaseScreen(
        toolbar = {
            AppToolbar(
                title = "New transaction",
                onNavigationIconClicked = {
                    navController.navigateUp()
                }
            )
        },
        content = {
            AddTransactionScreen()
        }
    )
}
package com.dimonkiv.savingstracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.ui.NavDisplay
import com.dimonkiv.savingstracker.core.navigation.BottomSheetSceneStrategy
import com.dimonkiv.savingstracker.core.navigation.Navigator
import com.dimonkiv.savingstracker.core.navigation.rememberNavigationState
import com.dimonkiv.savingstracker.core.navigation.toEntries
import com.dimonkiv.savingstracker.core.navigation.routes.Home
import com.dimonkiv.savingstracker.core.navigation.routes.TOP_LEVEL_ROUTES
import com.dimonkiv.savingstracker.designsystem.theme.AppTheme
import com.dimonkiv.savingstracker.feature.main.MainViewModel
import com.dimonkiv.savingstracker.feature.main.component.BottomNavigationBar
import org.koin.compose.koinInject
import org.koin.compose.navigation3.koinEntryProvider
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                val navigationState = rememberNavigationState(
                    startRoute = Home,
                    topLevelRoutes = TOP_LEVEL_ROUTES
                )
                val navigator = koinInject<Navigator>()
                remember(navigationState) { navigator.attach(navigationState) }

                val bottomItems by koinViewModel<MainViewModel>().uiState.collectAsStateWithLifecycle()
                val isAtTabRoot = navigationState.backStacks[navigationState.topLevelRoute]?.size == 1

                val display: @Composable () -> Unit = {
                    NavDisplay(
                        entries = navigationState.toEntries(entryProvider = koinEntryProvider()),
                        onBack = { navigator.goBack() },
                        sceneStrategies = listOf(BottomSheetSceneStrategy())
                    )
                }

                if (isAtTabRoot) {
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = bottomItems,
                                currentTopLevelKey = navigationState.topLevelRoute
                            ) { key ->
                                navigator.navigate(key)
                            }
                        }
                    ) { innerPadding ->
                        Box(Modifier.padding(innerPadding)) { display() }
                    }
                } else {
                    display()
                }
            }
        }
    }
}

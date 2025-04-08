package com.dimonkiv.savingstracker.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.dimonkiv.savingstracker.core.design_system.SavingsTrackingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SavingsTrackingTheme {
                val navController = rememberNavController()

                AppNavHost(navController = navController)
            }
        }

    }
}
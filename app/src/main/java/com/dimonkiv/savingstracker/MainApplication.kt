package com.dimonkiv.savingstracker

import android.app.Application
import android.util.Log
import com.dimonkiv.savingstracker.domain.repository.AccountTypeRepository
import com.dimonkiv.savingstracker.presentation.account.add_account.account_type.model.AccountTypeModel
import com.dimonkiv.savingstracker.presentation.core.design_system.Blue
import com.dimonkiv.savingstracker.presentation.core.design_system.Green
import com.dimonkiv.savingstracker.presentation.core.design_system.Orange
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class MainApplication : Application() {

    @Inject
    lateinit var repository: AccountTypeRepository

    private val applicationScope by lazy { CoroutineScope(SupervisorJob()) }

    override fun onCreate() {
        super.onCreate()
        initDb()
    }

    override fun onTerminate() {
        super.onTerminate()
        // Safe cleanup, though not strictly necessary for production apps
        applicationScope.cancel()
    }

    private fun initDb() {
        applicationScope.launch(Dispatchers.IO) {
            runCatching {
                repository.fetchAccountTypes()
            }.onSuccess { items ->
                if (items.isEmpty()) {
                    saveAccountTypes()
                }
            }.onFailure {
                saveAccountTypes()
            }
        }
    }

    private fun saveAccountTypes() {
        applicationScope.launch(Dispatchers.IO) {
            getTypes().forEach { type ->
                runCatching {
                    repository.createAccountType(type)
                }.onFailure {
                    Log.e("TEST", "Fail to insert type - $it")
                }
            }
        }
    }

    private fun getTypes(): List<AccountTypeModel> {
        return listOf(
            AccountTypeModel(id = 0, title = "Cash", color = Green),
            AccountTypeModel(id = 0, title = "Bank account", color = Blue),
            AccountTypeModel(id = 0, title = "Invest", color = Orange)
        )
    }
}

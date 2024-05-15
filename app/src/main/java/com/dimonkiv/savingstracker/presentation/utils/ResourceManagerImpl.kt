package com.dimonkiv.savingstracker.presentation.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceManagerImpl @Inject constructor(
    @ApplicationContext
    private val context: Context
): ResourceManager {
    override fun getString(resId: Int, value: Int): String {
        return context.resources.getString(resId, value)
    }
}
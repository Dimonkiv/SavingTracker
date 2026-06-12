package com.dimonkiv.savingstracker.core.utils

import android.content.Context

class ResourceManagerImpl(
    private val context: Context
): ResourceManager {
    override fun getString(resId: Int): String {
        return context.resources.getString(resId)
    }
    override fun getString(resId: Int, value: Int): String {
        return context.resources.getString(resId, value)
    }
}
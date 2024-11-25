package com.dimonkiv.savingstracker.core.utils

interface ResourceManager {
    fun getString(resId: Int): String

    fun getString(resId: Int, value: Int): String

}
package com.dimonkiv.savingstracker.core.utils

object NumberUtils {

    fun convertToInt(value: String): Int {
        return try {
            value.toInt()
        } catch (_: NumberFormatException) {
            0
        }
    }

    fun convertToLong(value: String): Long {
        return try {
            value.toLong()
        } catch (_: NumberFormatException) {
            0L
        }
    }
}
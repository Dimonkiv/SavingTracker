package com.dimonkiv.savingstracker.core.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateUtils {

    fun parseDate(date: Long): String {
        val formatter = SimpleDateFormat(FORMAT, Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date
        return formatter.format(calendar.time)
    }

    fun getCurrentDate(): String {
        val formatter = SimpleDateFormat(FORMAT, Locale.getDefault())
        val calendar = Calendar.getInstance()
        return formatter.format(calendar.time)
    }

    fun parseDateToMillis(dateString: String): Long {
        return try {
            val formatter = SimpleDateFormat(FORMAT, Locale.getDefault())
            val date = formatter.parse(dateString)
            date?.time ?: 0
        } catch (_ : Exception) {
            0
        }
    }

    private const val FORMAT = "MMMM dd"
}
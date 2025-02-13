package com.splunk.test.mobile.presentation.utils.date

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone
import javax.inject.Inject

class DateConverterImpl @Inject constructor() : DateConverter {

    private val isoFormat: DateFormat
        get() = SimpleDateFormat(ISO_8601, Locale.US).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

    private val localDateFormat: DateFormat
        get() = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())

    override fun formatDate(isoDate: String): String {
        return try {
            val date: Date = isoFormat.parse(isoDate) ?: return isoDate
            localDateFormat.format(date)
        } catch (e: Exception) {
            isoDate
        }
    }

    private companion object {
        const val ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    }
}
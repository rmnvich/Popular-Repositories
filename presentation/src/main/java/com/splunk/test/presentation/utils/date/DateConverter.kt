package com.splunk.test.presentation.utils.date

interface DateConverter {

    fun formatDate(isoDate: String): String
}
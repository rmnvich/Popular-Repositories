package com.splunk.test.presentation.utils.color

interface LanguageColorGenerator {

    fun getColorForLanguage(language: String): Int
}
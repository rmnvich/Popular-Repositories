package com.splunk.test.mobile.presentation.utils.color

import android.graphics.Color
import javax.inject.Inject
import kotlin.math.abs

class LanguageColorGeneratorImpl @Inject constructor() : LanguageColorGenerator {

    override fun getColorForLanguage(language: String): Int {
        val hash = abs(language.hashCode())

        val red = (hash and 0xFF0000) shr 16
        val green = (hash and 0x00FF00) shr 8
        val blue = (hash and 0x0000FF)

        return Color.rgb(
            /* red = */ 100 + (red % 156),
            /* green = */ 100 + (green % 156),
            /* blue = */ 100 + (blue % 156)
        )
    }
}

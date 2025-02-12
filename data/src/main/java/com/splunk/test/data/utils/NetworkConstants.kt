package com.splunk.test.data.utils

object NetworkConstants {
    //WARNING: Better to store in NDK, or at least in remote config.
    //I'll leave this here to keep things simple, considering this is a public API.
    const val BASE_URL = "https://api.github.com/"

    const val TIMEOUT_SECONDS = 30L
}
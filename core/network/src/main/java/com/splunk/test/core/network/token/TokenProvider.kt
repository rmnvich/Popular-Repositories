package com.splunk.test.core.network.token

interface TokenProvider {

    fun getToken(): String
}
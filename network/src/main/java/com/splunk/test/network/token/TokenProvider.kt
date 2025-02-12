package com.splunk.test.network.token

interface TokenProvider {

    fun getToken(): String
}
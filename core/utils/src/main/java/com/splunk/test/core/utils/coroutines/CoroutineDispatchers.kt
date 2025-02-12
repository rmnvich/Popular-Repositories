package com.splunk.test.core.utils.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.MainCoroutineDispatcher

interface CoroutineDispatchers {

    val main: MainCoroutineDispatcher

    val io: CoroutineDispatcher

    val default: CoroutineDispatcher
}
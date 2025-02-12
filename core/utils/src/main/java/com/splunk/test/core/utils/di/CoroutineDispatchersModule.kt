package com.splunk.test.core.utils.di

import com.splunk.test.core.utils.coroutines.CoroutineDispatchers
import com.splunk.test.core.utils.coroutines.DefaultCoroutineDispatchers
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoroutineDispatchersModule {

    @Binds
    fun bindCoroutineDispatchers(impl: DefaultCoroutineDispatchers): CoroutineDispatchers
}
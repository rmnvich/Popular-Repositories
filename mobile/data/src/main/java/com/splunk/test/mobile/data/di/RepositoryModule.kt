package com.splunk.test.mobile.data.di

import com.splunk.test.mobile.data.repository.TrendingRepositoryImpl
import com.splunk.test.mobile.domain.repository.TrendingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindTrendingRepository(impl: TrendingRepositoryImpl): TrendingRepository
}
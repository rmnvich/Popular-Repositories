package com.splunk.test.mobile.data.di

import com.splunk.test.mobile.data.datasource.GitHubRepositoryService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Provides
    @Singleton
    fun provideGitHubRepositoryService(retrofit: Retrofit): GitHubRepositoryService =
        retrofit.create(GitHubRepositoryService::class.java)
}
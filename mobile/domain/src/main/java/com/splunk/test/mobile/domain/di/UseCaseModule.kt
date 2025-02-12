package com.splunk.test.mobile.domain.di

import com.splunk.test.mobile.domain.get.GetTrendingRepositoriesUseCase
import com.splunk.test.mobile.domain.get.GetTrendingRepositoriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetTrendingRepositoriesUseCase(
        impl: GetTrendingRepositoriesUseCaseImpl,
    ): GetTrendingRepositoriesUseCase
}
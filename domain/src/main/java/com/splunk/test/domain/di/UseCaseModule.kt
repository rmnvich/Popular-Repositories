package com.splunk.test.domain.di

import com.splunk.test.domain.usecase.get.GetTrendingRepositoriesUseCase
import com.splunk.test.domain.usecase.get.GetTrendingRepositoriesUseCaseImpl
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
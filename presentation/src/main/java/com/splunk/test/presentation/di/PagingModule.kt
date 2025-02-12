package com.splunk.test.presentation.di

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.splunk.test.domain.model.GitHubRepository
import com.splunk.test.domain.usecase.get.GetTrendingRepositoriesUseCase
import com.splunk.test.presentation.paging.PagingConstants
import com.splunk.test.presentation.paging.TrendingRepositoriesPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object PagingModule {

    @Provides
    fun provideTrendingRepositoriesPagingSource(
        getTrendingRepositoriesUseCase: GetTrendingRepositoriesUseCase,
    ): PagingSource<Int, GitHubRepository> = TrendingRepositoriesPagingSource(
        getTrendingRepositoriesUseCase = getTrendingRepositoriesUseCase,
    )

    @Provides
    fun providePagingConfig(): PagingConfig = PagingConfig(
        pageSize = PagingConstants.TRENDING_REPOSITORIES_PAGE_SIZE,
        enablePlaceholders = PagingConstants.TRENDING_REPOSITORIES_ENABLE_PLACEHOLDERS,
    )
}
package com.splunk.test.mobile.presentation.di

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import com.splunk.test.mobile.domain.get.GetTrendingRepositoriesUseCase
import com.splunk.test.mobile.domain.model.GitHubRepository
import com.splunk.test.mobile.presentation.paging.PagingConstants
import com.splunk.test.mobile.presentation.paging.TrendingRepositoriesPagingSource
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
        prefetchDistance = PagingConstants.TRENDING_REPOSITORIES_PAGE_SIZE,
        initialLoadSize = PagingConstants.TRENDING_REPOSITORIES_PAGE_SIZE * 2,
        enablePlaceholders = PagingConstants.TRENDING_REPOSITORIES_ENABLE_PLACEHOLDERS,
    )
}
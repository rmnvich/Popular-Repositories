package com.splunk.test.presentation.di

import androidx.paging.PagingSource
import com.splunk.test.domain.model.GitHubRepository
import com.splunk.test.presentation.paging.TrendingRepositoriesPagingSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface PagingModule {

    @Binds
    fun bindTrendingRepositoriesPagingSource(
        impl: TrendingRepositoriesPagingSource,
    ): PagingSource<Int, GitHubRepository>
}
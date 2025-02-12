package com.splunk.test.presentation.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.splunk.test.domain.model.GitHubRepository
import com.splunk.test.domain.usecase.get.GetTrendingRepositoriesUseCase
import javax.inject.Inject

class TrendingRepositoriesPagingSource @Inject constructor(
    private val getTrendingRepositoriesUseCase: GetTrendingRepositoriesUseCase,
) : PagingSource<Int, GitHubRepository>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GitHubRepository> {
        val page = params.key ?: DEFAULT_PAGE
        return try {
            val repositories = getTrendingRepositoriesUseCase(
                page = page,
                pageSize = params.loadSize,
            )
            LoadResult.Page(
                data = repositories,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (repositories.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GitHubRepository>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    private companion object {
        const val DEFAULT_PAGE = 1
    }
}
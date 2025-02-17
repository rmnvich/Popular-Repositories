package com.splunk.test.mobile.presentation.screen.repository.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.splunk.test.core.utils.coroutines.CoroutineDispatchers
import com.splunk.test.mobile.presentation.mapper.RepositoryUiMapper
import com.splunk.test.mobile.presentation.model.RepositoryUiModel
import com.splunk.test.mobile.presentation.screen.repository.list.paging.TrendingRepositoriesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RepositoryListViewModel @Inject constructor(
    trendingRepositoriesPagingSource: TrendingRepositoriesPagingSource,
    trendingRepositoriesPagingConfig: PagingConfig,
    private val repositoryUiMapper: RepositoryUiMapper,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {

    val repositoriesPagingData: Flow<PagingData<RepositoryUiModel>> = Pager(
        config = trendingRepositoriesPagingConfig,
        pagingSourceFactory = { trendingRepositoriesPagingSource },
    ).flow
        .map { pagingData ->
            withContext(dispatchers.default) {
                pagingData.map { repository ->
                    repositoryUiMapper.map(repository)
                }
            }
        }
        .cachedIn(viewModelScope)
}
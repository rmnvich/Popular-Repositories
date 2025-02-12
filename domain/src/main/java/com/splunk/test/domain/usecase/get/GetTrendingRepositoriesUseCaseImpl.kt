package com.splunk.test.domain.usecase.get

import com.splunk.test.domain.model.GitHubRepository
import com.splunk.test.domain.repository.TrendingRepository
import javax.inject.Inject

class GetTrendingRepositoriesUseCaseImpl @Inject constructor(
    private val trendingRepository: TrendingRepository,
) : GetTrendingRepositoriesUseCase {

    override suspend fun invoke(page: Int, pageSize: Int): List<GitHubRepository> {
        return trendingRepository.loadTrendingRepositories(
            page = page,
            pageSize = pageSize,
        )
    }
}
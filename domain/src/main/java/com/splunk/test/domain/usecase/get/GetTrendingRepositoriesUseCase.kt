package com.splunk.test.domain.usecase.get

import com.splunk.test.domain.model.GitHubRepository

interface GetTrendingRepositoriesUseCase {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
    ): List<GitHubRepository>
}
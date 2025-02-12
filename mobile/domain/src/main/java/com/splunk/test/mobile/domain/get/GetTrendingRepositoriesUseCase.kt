package com.splunk.test.mobile.domain.get

import com.splunk.test.mobile.domain.model.GitHubRepository

interface GetTrendingRepositoriesUseCase {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
    ): List<GitHubRepository>
}
package com.splunk.test.domain.repository

import com.splunk.test.domain.model.GitHubRepository

interface TrendingRepository {

    suspend fun loadTrendingRepositories(
        page: Int,
        pageSize: Int,
    ): List<GitHubRepository>
}
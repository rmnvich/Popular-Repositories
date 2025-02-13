package com.splunk.test.mobile.domain.repository

import com.splunk.test.mobile.domain.model.GitHubRepository

interface TrendingRepository {

    suspend fun loadTrendingRepositories(
        page: Int,
        pageSize: Int,
    ): List<GitHubRepository>
}
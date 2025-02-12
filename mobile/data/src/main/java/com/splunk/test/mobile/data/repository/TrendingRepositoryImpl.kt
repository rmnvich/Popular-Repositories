package com.splunk.test.mobile.data.repository

import com.splunk.test.mobile.data.datasource.GitHubRepositoryService
import com.splunk.test.mobile.data.mapper.toDomainModel
import com.splunk.test.mobile.domain.model.GitHubRepository
import com.splunk.test.mobile.domain.repository.TrendingRepository
import javax.inject.Inject

class TrendingRepositoryImpl @Inject constructor(
    private val gitHubRepositoryService: GitHubRepositoryService,
) : TrendingRepository {

    override suspend fun loadTrendingRepositories(
        page: Int,
        pageSize: Int,
    ): List<GitHubRepository> {
        val response = gitHubRepositoryService.getTrendingRepositories(
            page = page,
            pageSize = pageSize,
        )
        return response.repositories.map { repository ->
            val languages = repository.languagesUrl?.let { url ->
                gitHubRepositoryService.getRepositoryLanguages(url)
            }
            repository.toDomainModel(languages)
        }
    }
}
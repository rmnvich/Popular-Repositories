package com.splunk.test.data.repository

import com.splunk.test.data.datasource.GitHubRepositoryService
import com.splunk.test.data.mapper.toDomainModel
import com.splunk.test.domain.model.GitHubRepository
import com.splunk.test.domain.repository.TrendingRepository
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
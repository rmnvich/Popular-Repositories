package com.splunk.test.mobile.data.mapper

import com.splunk.test.mobile.data.model.GitHubRepositoryResponse
import com.splunk.test.mobile.data.model.GitHubRepositoryResponse.OwnerResponse
import com.splunk.test.mobile.domain.model.GitHubRepository

fun GitHubRepositoryResponse.toDomainModel(
    languages: Map<String, Int>?,
): GitHubRepository = GitHubRepository(
    id = this.id,
    name = this.name,
    fullName = this.fullName,
    description = this.description,
    owner = this.owner.toDomainModel(),
    isPrivate = this.isPrivate,
    starCount = this.starCount,
    forkCount = this.forkCount,
    watcherCount = this.watcherCount,
    issueCount = this.issueCount,
    mainLanguage = this.language,
    allLanguages = languages?.keys,
    url = this.url,
    createdAt = this.createdAt,
)

private fun OwnerResponse.toDomainModel(): GitHubRepository.Owner = GitHubRepository.Owner(
    id = this.id,
    login = this.login,
    type = this.type,
    avatarUrl = this.avatarUrl,
)
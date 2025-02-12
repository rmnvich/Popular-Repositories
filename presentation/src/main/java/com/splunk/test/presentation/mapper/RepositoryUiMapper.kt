package com.splunk.test.presentation.mapper

import com.splunk.test.domain.model.GitHubRepository
import com.splunk.test.presentation.model.RepositoryUiModel

interface RepositoryUiMapper {

    fun map(model: GitHubRepository): RepositoryUiModel
}
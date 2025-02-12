package com.splunk.test.mobile.presentation.mapper

import com.splunk.test.mobile.domain.model.GitHubRepository
import com.splunk.test.mobile.presentation.model.RepositoryUiModel

interface RepositoryUiMapper {

    fun map(model: GitHubRepository): RepositoryUiModel
}
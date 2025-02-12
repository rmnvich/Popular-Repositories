package com.splunk.test.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrendingRepositoriesResponse(
    @Json(name = "items") val repositories: List<GitHubRepositoryResponse>,
)
package com.splunk.test.mobile.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TrendingRepositoriesResponse(
    @Json(name = "items") val repositories: List<GitHubRepositoryResponse>,
)
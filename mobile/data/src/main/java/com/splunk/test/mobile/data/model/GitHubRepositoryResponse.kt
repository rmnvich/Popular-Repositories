package com.splunk.test.mobile.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GitHubRepositoryResponse(
    @Json(name = "id") val id: Long,
    @Json(name = "name") val name: String,
    @Json(name = "full_name") val fullName: String,
    @Json(name = "description") val description: String?,
    @Json(name = "owner") val owner: OwnerResponse,
    @Json(name = "private") val isPrivate: Boolean,
    @Json(name = "languages_url") val languagesUrl: String?,
    @Json(name = "stargazers_count") val starCount: Int,
    @Json(name = "forks_count") val forkCount: Int,
    @Json(name = "html_url") val url: String,
    @Json(name = "created_at") val createdAt: String,
) {

    @JsonClass(generateAdapter = true)
    data class OwnerResponse(
        @Json(name = "id") val id: Long,
        @Json(name = "login") val login: String,
        @Json(name = "avatar_url") val avatarUrl: String?,
    )
}
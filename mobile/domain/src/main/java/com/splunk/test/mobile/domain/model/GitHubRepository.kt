package com.splunk.test.mobile.domain.model

data class GitHubRepository(
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String?,
    val owner: Owner,
    val isPrivate: Boolean,
    val starCount: Int,
    val forkCount: Int,
    val languages: Set<String>?,
    val url: String?,
    val createdAt: String,
) {

    data class Owner(
        val id: Long,
        val login: String,
        val avatarUrl: String?,
    )
}
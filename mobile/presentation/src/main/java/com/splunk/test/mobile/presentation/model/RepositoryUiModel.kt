package com.splunk.test.mobile.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepositoryUiModel(
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String?,
    val owner: OwnerUiModel,
    val isPrivate: Boolean,
    val starCountFormatted: String,
    val starCountShorten: String,
    val forkCountFormatted: String,
    val forkCountShorten: String,
    val watcherCountFormatted: String,
    val issueCountFormatted: String,
    val mainLanguage: LanguageUiModel?,
    val allLanguages: List<LanguageUiModel>?,
    val url: String?,
    val createdAt: String,
) : Parcelable {

    @Parcelize
    data class OwnerUiModel(
        val login: String,
        val type: String?,
        val avatarUrl: String?,
    ) : Parcelable

    @Parcelize
    data class LanguageUiModel(
        val name: String,
        val color: Int,
    ) : Parcelable
}
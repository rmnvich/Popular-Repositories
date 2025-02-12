package com.splunk.test.presentation.model

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
    val starCount: Int,
    val forkCount: Int,
    val languages: List<LanguageUiModel>?,
    val url: String?,
    val createdAt: String,
) : Parcelable {

    @Parcelize
    data class OwnerUiModel(
        val login: String,
        val avatarUrl: String?,
    ) : Parcelable

    @Parcelize
    data class LanguageUiModel(
        val name: String,
        val color: Int,
    ) : Parcelable
}
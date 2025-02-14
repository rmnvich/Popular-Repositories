package com.splunk.test.mobile.presentation.mapper

import android.annotation.SuppressLint
import com.splunk.test.mobile.domain.model.GitHubRepository
import com.splunk.test.mobile.presentation.model.RepositoryUiModel
import com.splunk.test.mobile.presentation.utils.color.LanguageColorGenerator
import com.splunk.test.mobile.presentation.utils.date.DateConverter
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

class RepositoryUiMapperImpl @Inject constructor(
    private val dateConverter: DateConverter,
    private val colorGenerator: LanguageColorGenerator,
) : RepositoryUiMapper {

    private val numberFormat: NumberFormat = NumberFormat.getInstance(Locale.getDefault())

    override fun map(model: GitHubRepository): RepositoryUiModel = RepositoryUiModel(
        id = model.id,
        name = model.name,
        fullName = model.fullName,
        description = model.description,
        owner = RepositoryUiModel.OwnerUiModel(
            login = model.owner.login,
            type = model.owner.type,
            avatarUrl = model.owner.avatarUrl,
        ),
        isPrivate = model.isPrivate,
        starCountFormatted = formatNumber(model.starCount),
        starCountShorten = shortenNumber(model.starCount),
        forkCountFormatted = formatNumber(model.forkCount),
        forkCountShorten = shortenNumber(model.forkCount),
        watcherCountFormatted = formatNumber(model.watcherCount),
        issueCountFormatted = formatNumber(model.issueCount),
        mainLanguage = model.mainLanguage?.toLanguageUiModel(),
        allLanguages = model.allLanguages?.map { it.toLanguageUiModel() },
        url = model.url,
        createdAt = dateConverter.formatDate(model.createdAt),
    )

    private fun String.toLanguageUiModel() = RepositoryUiModel.LanguageUiModel(
        name = this,
        color = colorGenerator.getColorForLanguage(this)
    )

    private fun formatNumber(value: Int): String = numberFormat.format(value)

    @SuppressLint("DefaultLocale")
    private fun shortenNumber(value: Int): String = when {
        value < 1_000 -> value.toString()
        value < 1_000_000 -> String.format("%.1fk", value / 1_000.0)
        value < 1_000_000_000 -> String.format("%.1fm", value / 1_000_000.0)
        else -> String.format("%.1fb", value / 1_000_000_000.0)
    }
}
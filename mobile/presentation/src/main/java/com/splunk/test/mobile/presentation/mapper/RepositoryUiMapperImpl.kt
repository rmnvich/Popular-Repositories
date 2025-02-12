package com.splunk.test.mobile.presentation.mapper

import com.splunk.test.mobile.domain.model.GitHubRepository
import com.splunk.test.mobile.presentation.model.RepositoryUiModel
import com.splunk.test.mobile.presentation.utils.color.LanguageColorGenerator
import com.splunk.test.mobile.presentation.utils.date.DateConverter
import javax.inject.Inject

class RepositoryUiMapperImpl @Inject constructor(
    private val dateConverter: DateConverter,
    private val colorGenerator: LanguageColorGenerator,
) : RepositoryUiMapper {

    override fun map(model: GitHubRepository): RepositoryUiModel = RepositoryUiModel(
        id = model.id,
        name = model.name,
        fullName = model.fullName,
        description = model.description,
        owner = RepositoryUiModel.OwnerUiModel(
            login = model.owner.login,
            avatarUrl = model.owner.avatarUrl,
        ),
        isPrivate = model.isPrivate,
        starCount = model.starCount,
        forkCount = model.forkCount,
        languages = model.languages?.map { language ->
            RepositoryUiModel.LanguageUiModel(
                name = language,
                color = colorGenerator.getColorForLanguage(language)
            )
        },
        url = model.url,
        createdAt = dateConverter.formatDate(model.createdAt),
    )
}
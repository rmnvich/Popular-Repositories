package com.splunk.test.mobile.presentation.screen.repository.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import com.splunk.test.mobile.presentation.model.RepositoryUiModel

@Preview
@Composable
fun RepositoryDetailsScreenPreview() {
    RepositoryDetailsScreen(
        uiModel = RepositoryUiModel(
            id = 0,
            name = "Splunk Test",
            fullName = "rmnvich / SplunkTest",
            description = "Home assignment for the Splunk company",
            owner = RepositoryUiModel.OwnerUiModel(
                login = "Vadzim Ramanovich",
                avatarUrl = "https://avatars.githubusercontent.com/u/33923854?v=4",
            ),
            isPrivate = true,
            starCount = 520780,
            starCountFormatted = "520.7k",
            forkCount = 107000,
            forkCountFormatted = "107.0k",
            mainLanguage = RepositoryUiModel.LanguageUiModel(
                name = "Kotlin",
                color = 13666500,
            ),
            allLanguages = null,
            url = null,
            createdAt = "Dec 24, 2014",
        ),
    )
}

@Composable
fun RepositoryDetailsScreen(viewModel: RepositoryDetailsViewModel) {
    val uiState = viewModel.uiState.collectAsState()
    RepositoryDetailsScreen(uiModel = uiState.value.uiModel)
}

@Composable
private fun RepositoryDetailsScreen(uiModel: RepositoryUiModel?) {
}
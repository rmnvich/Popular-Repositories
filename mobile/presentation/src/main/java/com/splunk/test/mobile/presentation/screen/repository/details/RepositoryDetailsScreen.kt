package com.splunk.test.mobile.presentation.screen.repository.details

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.splunk.test.mobile.presentation.R
import com.splunk.test.mobile.presentation.model.RepositoryUiModel
import kotlinx.coroutines.delay

private const val ON_BACK_PRESS_DELAY = 200L

@Composable
@ExperimentalMaterial3Api
@ExperimentalSharedTransitionApi
@ExperimentalLayoutApi
fun RepositoryDetailsScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: RepositoryDetailsViewModel,
    onClickBack: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    RepositoryDetailsScreen(
        sharedTransitionScope = sharedTransitionScope,
        animatedVisibilityScope = animatedVisibilityScope,
        uiModel = uiState.value.uiModel,
        onClickBack = onClickBack,
    )
}

@Composable
@ExperimentalMaterial3Api
@ExperimentalSharedTransitionApi
@ExperimentalLayoutApi
private fun RepositoryDetailsScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    uiModel: RepositoryUiModel?,
    onClickBack: () -> Unit,
) {
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    var isBackEnabled by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        // Delay to make animation happy
        delay(
            timeMillis = TRANSITION_ANIMATION_DELAY +
                    TRANSITION_ANIMATION_DURATION +
                    ON_BACK_PRESS_DELAY
        )
        isBackEnabled = true
    }
    BackHandler(enabled = true) {
        if (isBackEnabled) onClickBack()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
        RepositoryDetailsTopAppBar(
            scrollBehavior = scrollBehavior,
            onClickBack = { onBackPressedDispatcher?.onBackPressed() },
        )
        if (uiModel != null) {
            RepositoryDetailsContent(
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                scrollBehavior = scrollBehavior,
                uiModel = uiModel,
            )
        } else {
            ErrorState()
        }
    }
}

@Composable
private fun ErrorState() {
    Column(
        modifier = Modifier
            .padding(top = 128.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
        Icon(
            modifier = Modifier.size(96.dp),
            painter = painterResource(R.drawable.ic_report_24_outline),
            tint = color,
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = stringResource(R.string.something_went_wrong),
            color = color,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview
@Composable
@ExperimentalMaterial3Api
@ExperimentalSharedTransitionApi
@ExperimentalLayoutApi
private fun RepositoryDetailsScreenPreview() {
    SharedTransitionLayout {
        AnimatedVisibility(visible = true) {
            RepositoryDetailsScreen(
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedVisibilityScope = this@AnimatedVisibility,
                uiModel = RepositoryUiModel(
                    id = 0,
                    name = "Splunk Test",
                    fullName = "rmnvich / SplunkTest",
                    description = "Home assignment for the Splunk company",
                    owner = RepositoryUiModel.OwnerUiModel(
                        login = "Vadzim Ramanovich",
                        type = "Organization",
                        avatarUrl = "https://avatars.githubusercontent.com/u/33923854?v=4",
                    ),
                    isPrivate = true,
                    starCountFormatted = "520,780",
                    starCountShorten = "520.7k",
                    forkCountFormatted = "107,000",
                    forkCountShorten = "107.0k",
                    mainLanguage = RepositoryUiModel.LanguageUiModel(
                        name = "Kotlin",
                        color = 13666500,
                    ),
                    allLanguages = null,
                    url = null,
                    createdAt = "Dec 24, 2014",
                    issueCountFormatted = "208",
                    watcherCountFormatted = "30,601",
                ),
                onClickBack = {},
            )
        }
    }
}
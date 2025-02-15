@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.splunk.test.mobile.presentation.screen.repository.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.splunk.test.mobile.presentation.model.RepositoryUiModel
import com.splunk.test.mobile.presentation.theme.ThemeViewModel
import kotlinx.coroutines.flow.flowOf

@Composable
fun RepositoryListScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    repositoryListViewModel: RepositoryListViewModel,
    themeViewModel: ThemeViewModel,
    isDarkTheme: Boolean,
    onClickRepository: (RepositoryUiModel) -> Unit,
) {
    val repositoryItems = repositoryListViewModel.repositoriesPagingData.collectAsLazyPagingItems()
    RepositoryListScreen(
        sharedTransitionScope = sharedTransitionScope,
        animatedVisibilityScope = animatedVisibilityScope,
        repositoryItems = repositoryItems,
        isDarkTheme = isDarkTheme,
        onClickRepository = onClickRepository,
        onClickRetry = { repositoryItems.retry() },
        onClickToggleTheme = themeViewModel::onClickToggleTheme,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RepositoryListScreen(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    repositoryItems: LazyPagingItems<RepositoryUiModel>,
    isDarkTheme: Boolean,
    onClickRepository: (RepositoryUiModel) -> Unit,
    onClickRetry: () -> Unit,
    onClickToggleTheme: () -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            RepositoryListTopAppBar(
                scrollBehavior = scrollBehavior,
                isDarkTheme = isDarkTheme,
                onClickToggleTheme = onClickToggleTheme,
            )
        },
        content = { paddingValues ->
            RepositoryListContent(
                paddingValues = paddingValues,
                scrollBehavior = scrollBehavior,
                isDarkTheme = isDarkTheme,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                repositoryItems = repositoryItems,
                onClickRepository = onClickRepository,
                onClickRetry = onClickRetry,
            )
        }
    )
}

@Preview
@Composable
private fun RepositoryListScreenPreview() {
    SharedTransitionLayout {
        AnimatedVisibility(visible = true) {
            val flow = remember { flowOf(PagingData.empty<RepositoryUiModel>()) }
            RepositoryListScreen(
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedVisibilityScope = this@AnimatedVisibility,
                repositoryItems = flow.collectAsLazyPagingItems(),
                isDarkTheme = false,
                onClickRepository = {},
                onClickRetry = {},
                onClickToggleTheme = {}
            )
        }
    }
}
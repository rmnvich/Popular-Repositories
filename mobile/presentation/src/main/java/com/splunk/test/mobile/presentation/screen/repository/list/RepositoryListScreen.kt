package com.splunk.test.mobile.presentation.screen.repository.list

import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.splunk.test.mobile.presentation.model.RepositoryUiModel
import kotlinx.coroutines.flow.flowOf

private const val LABEL_TRANSITION_SCROLL_OFFSET = "transition_scroll_offset"
private const val TRANSITION_SCROLL_OFFSET_DURATION = 500

@Preview
@Composable
private fun RepositoryListScreenPreview() {
    val flow = remember { flowOf(PagingData.empty<RepositoryUiModel>()) }
    RepositoryListScreen(
        repositoryItems = flow.collectAsLazyPagingItems(),
        onClickRepository = {},
        onClickRetry = {},
    )
}

@Composable
fun RepositoryListScreen(viewModel: RepositoryListViewModel) {
    val repositoryItems = viewModel.repositoriesPagingData.collectAsLazyPagingItems()
    RepositoryListScreen(
        repositoryItems = repositoryItems,
        onClickRepository = viewModel::onClickRepository,
        onClickRetry = { repositoryItems.retry() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RepositoryListScreen(
    repositoryItems: LazyPagingItems<RepositoryUiModel>,
    onClickRepository: (RepositoryUiModel) -> Unit,
    onClickRetry: () -> Unit,
) {
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val topAppBarExpandedState by remember {
        derivedStateOf { topAppBarScrollBehavior.state.collapsedFraction < 0.5f }
    }
    val topAppBarExpandedStateTransition = updateTransition(
        targetState = topAppBarExpandedState,
        label = LABEL_TRANSITION_SCROLL_OFFSET,
    )
    val topAppBarTransitionSpec = tween<Dp>(
        durationMillis = TRANSITION_SCROLL_OFFSET_DURATION,
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
        topBar = {
            RepositoryListTopAppBar(
                topAppBarScrollBehavior = topAppBarScrollBehavior,
                topAppBarExpandedStateTransition = topAppBarExpandedStateTransition,
                topAppBarTransitionSpec = topAppBarTransitionSpec,
            )
        },
        content = { paddingValues ->
            RepositoryListContent(
                paddingValues = paddingValues,
                topAppBarExpandedStateTransition = topAppBarExpandedStateTransition,
                topAppBarTransitionSpec = topAppBarTransitionSpec,
                repositoryItems = repositoryItems,
                onClickRepository = onClickRepository,
                onClickRetry = onClickRetry,
            )
        }
    )
}
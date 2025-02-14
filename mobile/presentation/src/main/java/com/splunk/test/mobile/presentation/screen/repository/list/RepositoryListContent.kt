@file:OptIn(
    ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class,
    ExperimentalSharedTransitionApi::class
)

package com.splunk.test.mobile.presentation.screen.repository.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.splunk.test.mobile.presentation.R
import com.splunk.test.mobile.presentation.model.RepositoryUiModel
import com.splunk.test.mobile.presentation.screen.repository.list.paging.PagingConstants
import com.splunk.test.mobile.presentation.utils.widget.getSplunkTopAppBarCornerRadius
import com.splunk.test.mobile.presentation.utils.widget.shimmerBrush
import kotlinx.coroutines.flow.flowOf

@Composable
fun RepositoryListContent(
    paddingValues: PaddingValues,
    scrollBehavior: TopAppBarScrollBehavior,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    repositoryItems: LazyPagingItems<RepositoryUiModel>,
    onClickRepository: (RepositoryUiModel) -> Unit,
    onClickRetry: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(top = paddingValues.calculateTopPadding())
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        val cornerRadius = scrollBehavior.getSplunkTopAppBarCornerRadius()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clip(
                    shape = RoundedCornerShape(
                        topStart = cornerRadius,
                        topEnd = cornerRadius
                    ),
                )
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                contentPadding = PaddingValues(
                    top = 16.dp,
                    bottom = 256.dp,
                    start = 24.dp,
                    end = 24.dp,
                ),
            ) {
                item {
                    ListHeader()
                }
                when {
                    repositoryItems.loadState.source.refresh is LoadState.Loading -> {
                        items(PagingConstants.TRENDING_REPOSITORIES_PAGE_SIZE / 2) {
                            ShimmerLoadingItem()
                        }
                    }
                    repositoryItems.loadState.source.refresh is LoadState.Error -> {
                        item {
                            ErrorState(onRetry = onClickRetry)
                        }
                    }
                    repositoryItems.itemCount == 0 -> {
                        item {
                            EmptyState()
                        }
                    }
                    else -> {
                        repositoryItemsState(
                            sharedTransitionScope = sharedTransitionScope,
                            animatedVisibilityScope = animatedVisibilityScope,
                            repositoryItems = repositoryItems,
                            onClickRepository = onClickRepository,
                        )
                    }
                }
                if (repositoryItems.loadState.append is LoadState.Loading) {
                    item {
                        FooterProgressBar()
                    }
                } else if (repositoryItems.loadState.append is LoadState.Error) {
                    item {
                        FooterErrorState(onRetry = onClickRetry)
                    }
                }
            }
        }
    }
}

@Composable
private fun ListHeader() {
    Text(
        modifier = Modifier.padding(top = 24.dp),
        text = stringResource(R.string.title_trending),
        color = MaterialTheme.colorScheme.onBackground,
        style = MaterialTheme.typography.titleLarge,
        overflow = TextOverflow.Ellipsis,
        maxLines = 1,
    )
}

private fun LazyListScope.repositoryItemsState(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    repositoryItems: LazyPagingItems<RepositoryUiModel>,
    onClickRepository: (RepositoryUiModel) -> Unit,
) {
    items(
        count = repositoryItems.itemCount,
        key = { index -> repositoryItems[index]?.id ?: index },
    ) { index ->
        repositoryItems[index]?.let { uiModel ->
            RepositoryListItem(
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                uiModel = uiModel,
                onClickRepository = onClickRepository,
            )
        }
    }
}

@Composable
private fun ShimmerLoadingItem() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(196.dp)
            .background(
                brush = shimmerBrush(),
                shape = RoundedCornerShape(32.dp),
            )
    )
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier
            .padding(top = 128.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
        Icon(
            modifier = Modifier.size(96.dp),
            painter = painterResource(R.drawable.ic_block_24_outline),
            tint = color,
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = stringResource(R.string.no_repositories_found),
            color = color,
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Composable
private fun ErrorState(onRetry: () -> Unit) {
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
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = onRetry,
        ) {
            Text(
                text = stringResource(R.string.action_retry),
            )
        }
    }
}

@Composable
private fun FooterProgressBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun FooterErrorState(onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.something_went_wrong),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
            style = MaterialTheme.typography.bodyLarge,
        )
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = onRetry,
        ) {
            Text(
                text = stringResource(R.string.action_retry),
            )
        }
    }
}

@Preview
@Composable
private fun RepositoryListContentPreview() {
    SharedTransitionLayout {
        AnimatedVisibility(visible = true) {
            val flow = remember { flowOf(PagingData.empty<RepositoryUiModel>()) }
            RepositoryListContent(
                paddingValues = PaddingValues(),
                scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedVisibilityScope = this@AnimatedVisibility,
                repositoryItems = flow.collectAsLazyPagingItems(),
                onClickRepository = {},
                onClickRetry = {},
            )
        }
    }
}
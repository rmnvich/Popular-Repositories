@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.splunk.test.mobile.presentation.screen.repository.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.splunk.test.mobile.presentation.R
import com.splunk.test.mobile.presentation.model.RepositoryUiModel
import com.splunk.test.mobile.presentation.screen.repository.widget.LanguageItem

internal const val SHARED_KEY_REPOSITORY_CARD_CONTAINER = "repository_list_item_container_"

@Composable
fun LazyItemScope.RepositoryListItem(
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    uiModel: RepositoryUiModel,
    onClickRepository: (RepositoryUiModel) -> Unit,
) {
    with(sharedTransitionScope) {
        val shape = RoundedCornerShape(36.dp)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clickable { onClickRepository(uiModel) }
                .clip(shape)
                .animateItem(fadeOutSpec = null)
                .sharedElement(
                    state = rememberSharedContentState(key = "$SHARED_KEY_REPOSITORY_CARD_CONTAINER${uiModel.id}"),
                    animatedVisibilityScope = animatedVisibilityScope,
                ),
            shape = shape,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
            content = { RepositoryListItemContent(uiModel) }
        )
    }
}

@Composable
private fun RepositoryListItemContent(uiModel: RepositoryUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                if (!uiModel.owner.avatarUrl.isNullOrBlank()) {
                    Image(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .size(24.dp)
                            .clip(shape = CircleShape),
                        painter = rememberAsyncImagePainter(uiModel.owner.avatarUrl),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                    )
                }
                Text(
                    modifier = Modifier.padding(end = 8.dp),
                    text = uiModel.owner.login,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
            Text(
                text = uiModel.createdAt,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Text(
            text = uiModel.name,
            color = MaterialTheme.colorScheme.surfaceTint,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
        )
        if (!uiModel.description.isNullOrBlank()) {
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = uiModel.description,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (uiModel.mainLanguage != null) {
                LanguageItem(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .weight(1f),
                    language = uiModel.mainLanguage,
                )
            } else {
                Spacer(
                    modifier = Modifier.weight(1f),
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(R.drawable.ic_fork_24_outline),
                    tint = MaterialTheme.colorScheme.surfaceTint,
                    contentDescription = stringResource(R.string.label_forks),
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    text = uiModel.forkCountShorten,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                )

                Icon(
                    modifier = Modifier.padding(start = 16.dp),
                    painter = painterResource(R.drawable.ic_star_24_outline),
                    tint = MaterialTheme.colorScheme.surfaceTint,
                    contentDescription = stringResource(R.string.label_stars),
                )
                Text(
                    modifier = Modifier.padding(start = 2.dp),
                    text = uiModel.starCountShorten,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}

@Preview
@Composable
private fun RepositoryListItemPreview() {
    LazyColumn {
        items(1) {
            SharedTransitionLayout {
                AnimatedVisibility(visible = true) {
                    RepositoryListItem(
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
                        onClickRepository = {},
                    )
                }
            }
        }
    }
}
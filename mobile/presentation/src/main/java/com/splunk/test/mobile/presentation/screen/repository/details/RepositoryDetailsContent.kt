@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)

package com.splunk.test.mobile.presentation.screen.repository.details

import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.splunk.test.mobile.presentation.R
import com.splunk.test.mobile.presentation.model.RepositoryUiModel
import com.splunk.test.mobile.presentation.screen.repository.widget.LanguageItem
import com.splunk.test.mobile.presentation.utils.widget.getSplunkTopAppBarCornerRadius

@Composable
fun RepositoryDetailsContent(
    scrollBehavior: TopAppBarScrollBehavior,
    uiModel: RepositoryUiModel,
) {
    val cornerRadius = scrollBehavior.getSplunkTopAppBarCornerRadius()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .verticalScroll(rememberScrollState())
            .clip(
                shape = RoundedCornerShape(
                    topStart = cornerRadius,
                    topEnd = cornerRadius
                ),
            )
            .background(color = MaterialTheme.colorScheme.surfaceContainerLowest)
            .padding(all = 24.dp)
    ) {
        UserInfo(owner = uiModel.owner)
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = uiModel.fullName,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.headlineSmall,
        )
        if (!uiModel.url.isNullOrBlank()) {
            val context = LocalContext.current
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uiModel.url))
                    context.startActivity(intent)
                },
            ) {
                Text("Open in browser")
            }
        }
        if (!uiModel.description.isNullOrBlank()) {
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = stringResource(R.string.label_description),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.labelMedium,
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = uiModel.description,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        RepositoryMetaData(
            iconResId = R.drawable.ic_star_24_outline,
            labelResId = R.string.label_stars,
            value = uiModel.starCountFormatted,
        )
        RepositoryMetaData(
            iconResId = R.drawable.ic_fork_24_outline,
            labelResId = R.string.label_forks,
            value = uiModel.forkCountFormatted,
        )
        RepositoryMetaData(
            iconResId = R.drawable.ic_eye_24_outline,
            labelResId = R.string.label_watchers,
            value = uiModel.watcherCountFormatted,
        )
        RepositoryMetaData(
            iconResId = R.drawable.ic_bug_24_outline,
            labelResId = R.string.label_issues,
            value = uiModel.issueCountFormatted,
        )
        if (uiModel.isPrivate) {
            Text(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .background(
                        color = MaterialTheme.colorScheme.errorContainer,
                        shape = CircleShape,
                    )
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                text = stringResource(R.string.private_repository),
                color = MaterialTheme.colorScheme.onErrorContainer,
                style = MaterialTheme.typography.labelMedium,
            )
        }
        if (!uiModel.allLanguages.isNullOrEmpty()) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                uiModel.allLanguages.forEach { language ->
                    LanguageItem(language = language)
                }
            }
        }
        Text(
            modifier = Modifier
                .padding(top = 24.dp)
                .align(Alignment.End),
            text = stringResource(R.string.created_on, uiModel.createdAt),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
private fun UserInfo(owner: RepositoryUiModel.OwnerUiModel) {
    Row(modifier = Modifier.fillMaxWidth()) {
        if (!owner.avatarUrl.isNullOrBlank()) {
            Image(
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(64.dp)
                    .clip(shape = RoundedCornerShape(20.dp)),
                painter = rememberAsyncImagePainter(owner.avatarUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = owner.login,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.titleLarge,
            )
            if (!owner.type.isNullOrBlank()) {
                Text(
                    modifier = Modifier.padding(top = 2.dp),
                    text = owner.type,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
        }
    }
}

@Composable
private fun RepositoryMetaData(
    @DrawableRes iconResId: Int,
    @StringRes labelResId: Int,
    value: String,
) {
    val label = stringResource(labelResId)
    Text(
        modifier = Modifier.padding(top = 8.dp),
        text = label,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = MaterialTheme.typography.labelMedium,
    )
    Row(
        modifier = Modifier.padding(top = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(iconResId),
            tint = MaterialTheme.colorScheme.surfaceTint,
            contentDescription = label,
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = value,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
private fun RepositoryDetailsContentPreview() {
    RepositoryDetailsContent(
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        uiModel = RepositoryUiModel(
            id = 0,
            name = "Splunk Test",
            fullName = "rmnvich/SplunkTest",
            description = "Home assignment for the Splunk company",
            owner = RepositoryUiModel.OwnerUiModel(
                login = "Vadzim Ramanovich",
                type = "Organization",
                avatarUrl = "https://avatars.githubusercontent.com/u/33923854?v=4",
            ),
            isPrivate = false,
            starCountFormatted = "520,780",
            starCountShorten = "520.7k",
            forkCountFormatted = "107,000",
            forkCountShorten = "107.0k",
            mainLanguage = RepositoryUiModel.LanguageUiModel(
                name = "Kotlin",
                color = 13666500,
            ),
            allLanguages = listOf(
                RepositoryUiModel.LanguageUiModel(
                    name = "Kotlin",
                    color = Color.Red.toArgb(),
                ),
                RepositoryUiModel.LanguageUiModel(
                    name = "Java",
                    color = Color.Blue.toArgb(),
                ),
                RepositoryUiModel.LanguageUiModel(
                    name = "C++",
                    color = Color.Green.toArgb(),
                ),
                RepositoryUiModel.LanguageUiModel(
                    name = "Python",
                    color = Color.Magenta.toArgb(),
                )
            ),
            url = "https://github.com/rmnvich/SplunkTest",
            createdAt = "Dec 24, 2014",
            issueCountFormatted = "208",
            watcherCountFormatted = "30,601",
        ),
    )
}
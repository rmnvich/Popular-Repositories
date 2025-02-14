@file:OptIn(ExperimentalMaterial3Api::class)

package com.splunk.test.mobile.presentation.screen.repository.list

import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.splunk.test.mobile.presentation.R
import com.splunk.test.mobile.presentation.utils.widget.FlexibleTopBarColors
import com.splunk.test.mobile.presentation.utils.widget.SplunkTopAppBar

private const val LABEL_PADDING_VERTICAL_ANIMATION = "animation_padding_vertical"

@Preview
@Composable
private fun RepositoryListTopAppBarPreview() {
    RepositoryListTopAppBar(
        topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        topAppBarExpandedStateTransition = updateTransition(true, ""),
        topAppBarTransitionSpec = tween(),
        isDarkTheme = true,
        onClickToggleTheme = {},
    )
}

@Composable
fun RepositoryListTopAppBar(
    topAppBarScrollBehavior: TopAppBarScrollBehavior,
    topAppBarExpandedStateTransition: Transition<Boolean>,
    topAppBarTransitionSpec: DurationBasedAnimationSpec<Dp>,
    isDarkTheme: Boolean,
    onClickToggleTheme: () -> Unit,
) {
    val paddingVertical by topAppBarExpandedStateTransition.animateDp(
        transitionSpec = { topAppBarTransitionSpec },
        label = LABEL_PADDING_VERTICAL_ANIMATION,
    ) { isExpanded ->
        if (isExpanded) 8.dp else 0.dp
    }

    SplunkTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = MaterialTheme.colorScheme.primary)
            .statusBarsPadding()
            .padding(vertical = paddingVertical),
        scrollBehavior = topAppBarScrollBehavior,
        colors = FlexibleTopBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            start = 24.dp,
                            end = 8.dp,
                            top = 12.dp,
                            bottom = 12.dp,
                        ),
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onPrimary,
                )
                IconButton(
                    modifier = Modifier.padding(end = 8.dp),
                    onClick = onClickToggleTheme,
                ) {
                    if (isDarkTheme) {
                        Icon(
                            painter = painterResource(R.drawable.ic_light_mode_24_outline),
                            tint = MaterialTheme.colorScheme.onPrimary,
                            contentDescription = stringResource(R.string.light_mode),
                        )
                    } else {
                        Icon(
                            painter = painterResource(R.drawable.ic_dark_mode_24_outline),
                            tint = MaterialTheme.colorScheme.onPrimary,
                            contentDescription = stringResource(R.string.dark_mode),
                        )
                    }
                }
            }
        }
    )
}
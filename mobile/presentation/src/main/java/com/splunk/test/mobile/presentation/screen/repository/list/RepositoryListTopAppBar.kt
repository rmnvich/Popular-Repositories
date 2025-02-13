@file:OptIn(ExperimentalMaterial3Api::class)

package com.splunk.test.mobile.presentation.screen.repository.list

import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.splunk.test.mobile.presentation.R
import com.splunk.test.mobile.theme.SplunkTestTheme

private const val LABEL_PADDING_VERTICAL_ANIMATION = "animation_padding_vertical"

@Preview
@Composable
private fun RepositoryListTopAppBarPreview() {
    SplunkTestTheme {
        RepositoryListTopAppBar(
            topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
            topAppBarExpandedStateTransition = updateTransition(true, ""),
            topAppBarTransitionSpec = tween(),
        )
    }
}

@Composable
fun RepositoryListTopAppBar(
    topAppBarScrollBehavior: TopAppBarScrollBehavior,
    topAppBarExpandedStateTransition: Transition<Boolean>,
    topAppBarTransitionSpec: DurationBasedAnimationSpec<Dp>,
) {
    val paddingVertical by topAppBarExpandedStateTransition.animateDp(
        transitionSpec = { topAppBarTransitionSpec },
        label = LABEL_PADDING_VERTICAL_ANIMATION,
    ) { isExpanded ->
        if (isExpanded) 8.dp else 0.dp
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = MaterialTheme.colorScheme.primary)
    ) {
        TopAppBar(
            modifier = Modifier.padding(vertical = paddingVertical),
            expandedHeight = 56.dp,
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    color = MaterialTheme.colorScheme.onPrimary,
                )
            },
            scrollBehavior = topAppBarScrollBehavior,
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                scrolledContainerColor = MaterialTheme.colorScheme.primary,
            ),
        )
    }
}
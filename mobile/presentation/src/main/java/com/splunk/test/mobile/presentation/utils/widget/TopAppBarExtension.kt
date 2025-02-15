package com.splunk.test.mobile.presentation.utils.widget

import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Transition
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private const val LABEL_TRANSITION_SCROLL_OFFSET = "top_app_bar_transition_scroll_offset"
private const val LABEL_CONTENT_CORNER_RADIUS_ANIMATION = "top_app_bar_animation_content_corner_radius"
private const val LABEL_PADDING_VERTICAL_ANIMATION = "top_app_bar_animation_padding_vertical"

private const val TOP_APP_BAR_TRANSITION_DURATION = 500

@Composable
@ExperimentalMaterial3Api
fun TopAppBarScrollBehavior.getExpandedStateTransition(
    fractionThreshold: Float = 0.5f,
): Transition<Boolean> {
    val topAppBarExpandedState by remember {
        derivedStateOf { state.collapsedFraction < fractionThreshold }
    }
    return updateTransition(
        targetState = topAppBarExpandedState,
        label = LABEL_TRANSITION_SCROLL_OFFSET,
    )
}

@Composable
@ExperimentalMaterial3Api
fun TopAppBarScrollBehavior.getSplunkTopAppBarCornerRadius(
    fractionThreshold: Float = 0.5f,
    transitionSpec: FiniteAnimationSpec<Dp> = tween(TOP_APP_BAR_TRANSITION_DURATION),
): Dp {
    val transition = this.getExpandedStateTransition(fractionThreshold)
    val cornerRadius by transition.animateDp(
        transitionSpec = { transitionSpec },
        label = LABEL_CONTENT_CORNER_RADIUS_ANIMATION,
    ) { isExpanded ->
        if (isExpanded) 36.dp else 0.dp
    }
    return cornerRadius
}

@Composable
@ExperimentalMaterial3Api
fun TopAppBarScrollBehavior.getSplunkTopAppBarVerticalPadding(
    fractionThreshold: Float = 0.5f,
    transitionSpec: FiniteAnimationSpec<Dp> = tween(TOP_APP_BAR_TRANSITION_DURATION),
): Dp {
    val transition = this.getExpandedStateTransition(fractionThreshold)
    val paddingVertical by transition.animateDp(
        transitionSpec = { transitionSpec },
        label = LABEL_PADDING_VERTICAL_ANIMATION,
    ) { isExpanded ->
        if (isExpanded) 8.dp else 0.dp
    }
    return paddingVertical
}
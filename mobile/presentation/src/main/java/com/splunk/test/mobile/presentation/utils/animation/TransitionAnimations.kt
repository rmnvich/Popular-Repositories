package com.splunk.test.mobile.presentation.utils.animation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavBackStackEntry

private const val ANIMATION_DURATION = 350

val enterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInVertically(
        initialOffsetY = { it / 8 },
        animationSpec = tween(durationMillis = ANIMATION_DURATION)
    ) + fadeIn(
        animationSpec = tween(durationMillis = ANIMATION_DURATION),
        initialAlpha = 0.4f,
    )
}

val exitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    fadeOut(
        animationSpec = tween(durationMillis = ANIMATION_DURATION),
    )
}

val popEnterTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    fadeIn(
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION,
            easing = FastOutLinearInEasing,
        ),
    )
}

val popExitTransition: AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutVertically(
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION,
            easing = FastOutLinearInEasing,
        ),
        targetOffsetY = { it / 8 }
    ) + fadeOut(
        animationSpec = tween(
            durationMillis = ANIMATION_DURATION,
            easing = FastOutLinearInEasing,
        ),
    )
}
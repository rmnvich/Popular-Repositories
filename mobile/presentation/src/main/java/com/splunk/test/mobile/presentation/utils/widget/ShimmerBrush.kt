package com.splunk.test.mobile.presentation.utils.widget

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

private const val LABEL_SHIMMER_TRANSITION = "shimmer_transition"
private const val LABEL_SHIMMER_TRANSLATE_ANIMATION = "shimmer_translate_animation"

@Composable
fun shimmerBrush(width: Float = 1000f): Brush {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val transition = rememberInfiniteTransition(label = LABEL_SHIMMER_TRANSITION)
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = width,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500),
            repeatMode = RepeatMode.Reverse,
        ),
        label = LABEL_SHIMMER_TRANSLATE_ANIMATION,
    )
    return Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnimation.value, y = translateAnimation.value)
    )
}
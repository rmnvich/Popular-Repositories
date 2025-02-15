package com.splunk.test.mobile.presentation.utils.widget

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun ThrottledIconButton(
    modifier: Modifier = Modifier,
    throttleInterval: Long = 300L,
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    var lastClickTime by remember { mutableLongStateOf(0L) }
    IconButton(
        modifier = modifier,
        onClick = {
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastClickTime >= throttleInterval) {
                lastClickTime = currentTime
                onClick()
            }
        },
        content = content,
    )
}
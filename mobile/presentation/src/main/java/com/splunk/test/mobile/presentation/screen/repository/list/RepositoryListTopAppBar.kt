package com.splunk.test.mobile.presentation.screen.repository.list

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splunk.test.mobile.presentation.R
import com.splunk.test.mobile.presentation.utils.widget.FlexibleTopBarColors
import com.splunk.test.mobile.presentation.utils.widget.SplunkTopAppBar
import com.splunk.test.mobile.presentation.utils.widget.ThrottledIconButton
import com.splunk.test.mobile.presentation.utils.widget.getSplunkTopAppBarVerticalPadding

private const val LABEL_ICON_THEME_ROTATION_ANIMATION = "animation_icon_theme_rotation"
private const val SWITCH_THEME_THROTTLE_MILLIS = 500L

@Composable
@ExperimentalMaterial3Api
fun RepositoryListTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    isDarkTheme: Boolean,
    onClickToggleTheme: () -> Unit,
) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (isDarkTheme) 90f else 0f,
        label = LABEL_ICON_THEME_ROTATION_ANIMATION,
    )
    SplunkTopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = MaterialTheme.colorScheme.primary)
            .statusBarsPadding()
            .padding(vertical = scrollBehavior.getSplunkTopAppBarVerticalPadding()),
        scrollBehavior = scrollBehavior,
        colors = FlexibleTopBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
        ),
        content = {
            Row(verticalAlignment = Alignment.CenterVertically) {
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
                ThrottledIconButton(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .graphicsLayer(rotationZ = rotationAngle),
                    throttleInterval = SWITCH_THEME_THROTTLE_MILLIS,
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

@Preview
@Composable
@ExperimentalMaterial3Api
private fun RepositoryListTopAppBarPreview() {
    RepositoryListTopAppBar(
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        isDarkTheme = true,
        onClickToggleTheme = {},
    )
}
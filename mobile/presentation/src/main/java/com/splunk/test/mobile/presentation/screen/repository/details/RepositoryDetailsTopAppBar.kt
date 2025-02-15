package com.splunk.test.mobile.presentation.screen.repository.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splunk.test.mobile.presentation.R
import com.splunk.test.mobile.presentation.utils.widget.ThrottledIconButton
import com.splunk.test.mobile.presentation.utils.widget.getSplunkTopAppBarVerticalPadding

@Composable
@ExperimentalMaterial3Api
fun RepositoryDetailsTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    onClickBack: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.padding(
            vertical = scrollBehavior.getSplunkTopAppBarVerticalPadding(),
        ),
        scrollBehavior = scrollBehavior,
        expandedHeight = 72.dp,
        title = {
            Text(
                text = stringResource(R.string.title_repository),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        },
        navigationIcon = {
            ThrottledIconButton(onClick = onClickBack) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_back_24_outline),
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = stringResource(R.string.action_back),
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.primary,
        )
    )
}

@Preview
@Composable
@ExperimentalMaterial3Api
private fun RepositoryDetailsTopAppBarPreview() {
    RepositoryDetailsTopAppBar(
        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        onClickBack = {},
    )
}
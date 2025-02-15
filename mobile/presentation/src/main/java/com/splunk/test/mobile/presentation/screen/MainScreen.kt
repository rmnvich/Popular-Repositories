package com.splunk.test.mobile.presentation.screen

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.splunk.test.mobile.presentation.screen.repository.details.RepositoryDetailsScreen
import com.splunk.test.mobile.presentation.screen.repository.list.RepositoryListScreen
import com.splunk.test.mobile.presentation.theme.ThemeViewModel
import com.splunk.test.mobile.presentation.utils.navigation.NavigationConstants
import com.splunk.test.mobile.presentation.utils.navigation.navigate

@Composable
@ExperimentalLayoutApi
@ExperimentalMaterial3Api
@ExperimentalSharedTransitionApi
fun MainScreen(
    themeViewModel: ThemeViewModel,
    isDarkTheme: Boolean,
) {
    val navController = rememberNavController()
    SharedTransitionLayout {
        NavHost(
            modifier = Modifier.background(color = MaterialTheme.colorScheme.primary),
            navController = navController,
            startDestination = NavigationConstants.ROUTE_REPOSITORY_LIST,
        ) {
            composable(route = NavigationConstants.ROUTE_REPOSITORY_LIST) {
                RepositoryListScreen(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable,
                    repositoryListViewModel = hiltViewModel(),
                    themeViewModel = themeViewModel,
                    isDarkTheme = isDarkTheme,
                    onClickRepository = { repository ->
                        navController.navigate(
                            route = NavigationConstants.ROUTE_REPOSITORY_DETAILS,
                            args = bundleOf(NavigationConstants.ARG_REPOSITORY to repository)
                        )
                    },
                )
            }
            composable(route = NavigationConstants.ROUTE_REPOSITORY_DETAILS) {
                RepositoryDetailsScreen(
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable,
                    viewModel = hiltViewModel(),
                    onClickBack = { navController.popBackStack() },
                )
            }
        }
    }
}
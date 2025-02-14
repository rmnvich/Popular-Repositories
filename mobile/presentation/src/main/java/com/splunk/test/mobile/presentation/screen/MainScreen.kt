package com.splunk.test.mobile.presentation.screen

import androidx.compose.foundation.background
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
import com.splunk.test.mobile.presentation.utils.animation.enterTransition
import com.splunk.test.mobile.presentation.utils.animation.exitTransition
import com.splunk.test.mobile.presentation.utils.animation.popEnterTransition
import com.splunk.test.mobile.presentation.utils.animation.popExitTransition
import com.splunk.test.mobile.presentation.utils.navigation.NavigationConstants
import com.splunk.test.mobile.presentation.utils.navigation.navigate

@Composable
fun MainScreen(
    themeViewModel: ThemeViewModel,
    isDarkTheme: Boolean,
) {

    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.primary),
        navController = navController,
        startDestination = NavigationConstants.ROUTE_REPOSITORY_LIST,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
    ) {
        composable(route = NavigationConstants.ROUTE_REPOSITORY_LIST) {
            RepositoryListScreen(
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
                viewModel = hiltViewModel(),
                onClickBack = { navController.popBackStack() },
            )
        }
    }
}
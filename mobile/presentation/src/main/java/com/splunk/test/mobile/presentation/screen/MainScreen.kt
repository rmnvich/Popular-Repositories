package com.splunk.test.mobile.presentation.screen

import androidx.compose.runtime.Composable
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
fun MainScreen(
    themeViewModel: ThemeViewModel,
    isDarkTheme: Boolean,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavigationConstants.ROUTE_REPOSITORY_LIST,
    ) {
        composable(NavigationConstants.ROUTE_REPOSITORY_LIST) {
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
        composable(NavigationConstants.ROUTE_REPOSITORY_DETAILS) {
            RepositoryDetailsScreen(
                viewModel = hiltViewModel(),
                onClickBack = { navController.popBackStack() },
            )
        }
    }
}
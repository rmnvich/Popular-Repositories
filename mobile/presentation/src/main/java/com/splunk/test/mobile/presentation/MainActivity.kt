package com.splunk.test.mobile.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.splunk.test.mobile.presentation.screen.repository.list.RepositoryListScreen
import com.splunk.test.mobile.presentation.theme.ThemeViewModel
import com.splunk.test.mobile.presentation.utils.reveal.CircularReveal
import com.splunk.test.mobile.theme.SplunkTestTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel = hiltViewModel<ThemeViewModel>()
            val isDarkThemeState = themeViewModel.isDarkTheme.collectAsState()
            CircularReveal(
                targetState = isDarkThemeState.value,
                animationSpec = tween(SWITCH_THEME_DURATION),
            ) { isDarkTheme ->
                SplunkTestTheme(isDarkTheme) {
                    RepositoryListScreen(
                        repositoryListViewModel = hiltViewModel(),
                        themeViewModel = themeViewModel,
                        isDarkTheme = isDarkTheme,
                    )
                }
            }
        }
    }

    private companion object {
        const val SWITCH_THEME_DURATION = 500
    }
}
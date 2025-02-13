package com.splunk.test.mobile.presentation.theme

import android.app.Application
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking

class ThemeDataStoreImpl @AssistedInject constructor(
    private val application: Application,
    @Assisted private val viewModelScope: CoroutineScope,
) : ThemeDataStore {

    private val Application.dataStore by preferencesDataStore(PREFERENCES_NAME)

    private val keyTheme = booleanPreferencesKey(KEY_IS_DARK_THEME)

    private val initialTheme: Boolean = runBlocking {
        application.dataStore.data.firstOrNull()?.get(keyTheme) ?: false
    }

    override val isDarkTheme: StateFlow<Boolean> = application.dataStore.data
        .map { preferences -> preferences[keyTheme] ?: false }
        .stateIn(viewModelScope, SharingStarted.Eagerly, initialTheme)

    override suspend fun saveTheme(isDark: Boolean) {
        application.dataStore.edit { preferences ->
            preferences[keyTheme] = isDark
        }
    }

    private companion object {
        const val PREFERENCES_NAME = "theme_prefs"
        const val KEY_IS_DARK_THEME = "is_dark_theme"
    }
}
package com.splunk.test.mobile.presentation.theme

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
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
    private val dataStore: DataStore<Preferences>,
    @Assisted private val viewModelScope: CoroutineScope,
) : ThemeDataStore {

    private val keyTheme = booleanPreferencesKey(KEY_IS_DARK_THEME)

    private val initialTheme: Boolean = runBlocking {
        dataStore.data.firstOrNull()?.get(keyTheme) ?: false
    }

    override val isDarkTheme: StateFlow<Boolean> = dataStore.data
        .map { preferences -> preferences[keyTheme] ?: false }
        .stateIn(viewModelScope, SharingStarted.Eagerly, initialTheme)

    override suspend fun saveTheme(isDark: Boolean) {
        dataStore.edit { preferences ->
            preferences[keyTheme] = isDark
        }
    }

    companion object {
        const val KEY_IS_DARK_THEME = "is_dark_theme"
    }
}
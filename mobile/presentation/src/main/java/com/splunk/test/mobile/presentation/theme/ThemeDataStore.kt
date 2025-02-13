package com.splunk.test.mobile.presentation.theme

import dagger.assisted.AssistedFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow

interface ThemeDataStore {

    @AssistedFactory
    interface Factory {
        fun create(viewModelScope: CoroutineScope): ThemeDataStoreImpl
    }

    val isDarkTheme: StateFlow<Boolean>

    suspend fun saveTheme(isDark: Boolean)
}
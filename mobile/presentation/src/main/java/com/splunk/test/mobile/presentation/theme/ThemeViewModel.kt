package com.splunk.test.mobile.presentation.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.splunk.test.core.utils.coroutines.CoroutineDispatchers
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    themeDataStoreFactory: ThemeDataStore.Factory,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {

    private val themeDataStore: ThemeDataStore = themeDataStoreFactory.create(viewModelScope)

    val isDarkTheme: StateFlow<Boolean> = themeDataStore.isDarkTheme

    fun onClickToggleTheme() {
        viewModelScope.launch(dispatchers.io) {
            themeDataStore.saveTheme(!isDarkTheme.value)
        }
    }
}
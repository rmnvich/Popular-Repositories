package com.splunk.test.mobile.presentation.screen.repository.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.splunk.test.core.utils.coroutines.CoroutineDispatchers
import com.splunk.test.mobile.presentation.model.RepositoryUiModel
import com.splunk.test.mobile.presentation.utils.navigation.NavigationConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val dispatchers: CoroutineDispatchers,
) : ViewModel() {

    data class RepositoryDetailsUiState(
        val uiModel: RepositoryUiModel? = null,
    )

    private val _uiState: MutableStateFlow<RepositoryDetailsUiState> = MutableStateFlow(RepositoryDetailsUiState())
    val uiState: StateFlow<RepositoryDetailsUiState> = _uiState

    init {
        viewModelScope.launch(dispatchers.default) {
            val uiModel = savedStateHandle.get<RepositoryUiModel>(
                key = NavigationConstants.ARG_REPOSITORY,
            )
            _uiState.update {
                it.copy(uiModel = uiModel)
            }
        }
    }
}
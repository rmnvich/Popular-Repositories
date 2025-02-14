package com.splunk.test.mobile.presentation.screen.repository.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.splunk.test.mobile.presentation.model.RepositoryUiModel
import com.splunk.test.mobile.presentation.utils.navigation.NavigationConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    data class RepositoryDetailsUiState(
        val uiModel: RepositoryUiModel? = null,
    )

    private val _uiState: MutableStateFlow<RepositoryDetailsUiState> = MutableStateFlow(RepositoryDetailsUiState())
    val uiState: StateFlow<RepositoryDetailsUiState> = _uiState

    init {
        val uiModel = savedStateHandle.get<RepositoryUiModel>(
            key = NavigationConstants.ARG_REPOSITORY,
        )
        _uiState.update {
            it.copy(uiModel = uiModel)
        }
    }
}
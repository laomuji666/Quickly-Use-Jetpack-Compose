package com.laomuji666.compose.quickly

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laomuji666.compose.core.logic.DemoLogic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _mainUiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState = _mainUiState.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),MainUiState.Loading)

    init {
        viewModelScope.launch {
            _mainUiState.value = MainUiState.Success(DemoLogic().requestMainText())
        }
    }
}

sealed interface MainUiState{
    data object Loading:MainUiState
    data class Success(val text:String):MainUiState
}
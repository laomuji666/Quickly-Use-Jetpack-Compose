package com.laomuji666.compose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laomuji666.compose.core.ui.stateInTimeout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * [HiltViewModel],[Inject] 注入ViewModel
 */
@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    /**
     * MainActivity的状态
     * 默认[MainUiState.Loading]
     */
    private val _mainUiState = MutableStateFlow<MainUiState>(MainUiState.Loading)
    val uiState = _mainUiState.stateInTimeout(
        viewModelScope,
        MainUiState.Loading
    )

    /**
     * 初始化三方SDK
     */
    init {
        viewModelScope.launch {
            //假设在这里初始化一些三方SDK
            delay(500)
            _mainUiState.value = MainUiState.Success
        }
    }
}

/**
 * MainActivity的状态
 * 只会是[MainUiState.Loading],[MainUiState.Success]
 */
sealed interface MainUiState{
    data object Loading: MainUiState
    data object Success: MainUiState
}
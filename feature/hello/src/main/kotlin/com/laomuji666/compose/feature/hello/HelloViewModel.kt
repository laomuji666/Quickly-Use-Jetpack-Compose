package com.laomuji666.compose.feature.hello

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laomuji666.compose.core.logic.HelloRepository
import com.laomuji666.compose.core.ui.stateInTimeout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HelloViewModel @Inject constructor() : ViewModel() {
    private val _helloText = HelloRepository().requestHelloText()

    val uiState = _helloText.map {
        HelloUiState(it)
    }.stateInTimeout(viewModelScope, HelloUiState())
}
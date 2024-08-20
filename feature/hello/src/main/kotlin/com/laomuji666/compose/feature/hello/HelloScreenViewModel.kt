package com.laomuji666.compose.feature.hello

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laomuji666.compose.core.logic.HelloRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HelloScreenViewModel @Inject constructor() : ViewModel() {
    private val _helloText = HelloRepository().requestHelloText()

    val uiState = _helloText.map {
        HelloUiState(it)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), HelloUiState())
}
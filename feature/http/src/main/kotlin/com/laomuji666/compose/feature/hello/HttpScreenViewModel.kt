package com.laomuji666.compose.feature.hello

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laomuji666.compose.core.logic.http.ConnectivityObserver
import com.laomuji666.compose.core.logic.http.HttpRepository
import com.laomuji666.compose.core.logic.http.Result
import com.laomuji666.compose.core.logic.http.request.CreateUserRequest
import com.laomuji666.compose.core.ui.stateInTimeout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HttpScreenViewModel @Inject constructor(
    private val httpRepository: HttpRepository,
    connectivityObserver: ConnectivityObserver
) : ViewModel() {
    private val _isError = MutableStateFlow(false)
    private val _isLoading = MutableStateFlow(false)
    private val _isConnect = connectivityObserver.isConnected
    private val _responseText = MutableStateFlow("")

    val uiState = combine(
        _isError,
        _isLoading,
        _isConnect,
        _responseText
    ){ isError,isLoading,isConnect,responseText ->
        HttpScreenUiState(
            isError = isError,
            isLoading = isLoading,
            isConnect = isConnect,
            responseText = responseText
        )
    }.stateInTimeout(viewModelScope, HttpScreenUiState())

    fun getListUsers(){
        if(_isLoading.value){
            return
        }

        viewModelScope.launch {
            httpRepository.delayRequest().collect{
                when(it){
                    is Result.Error -> {
                        _isLoading.value = false
                        _isError.value = true
                    }
                    Result.Loading -> {
                        _isLoading.value = true
                    }
                    is Result.Success -> {
                        _isLoading.value = false
                        _responseText.value = it.data
                    }
                }
            }
        }
    }

    fun createUser(){
        if(_isLoading.value){
            return
        }

        viewModelScope.launch {
            httpRepository.createUser(
                CreateUserRequest(
                    name = "ZhangSan",
                    job = "android"
                )
            ).collect{
                when(it){
                    is Result.Error -> {
                        _isLoading.value = false
                        _isError.value = true
                    }
                    Result.Loading -> {
                        _isLoading.value = true
                    }
                    is Result.Success -> {
                        _isLoading.value = false
                        _responseText.value = it.data
                    }
                }
            }
        }
    }
}
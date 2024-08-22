package com.laomuji666.compose.feature.hello

import com.laomuji666.compose.core.logic.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class HttpViewModel @Inject constructor(
    private val httpRepository: HttpRepository
) : ViewModel() {
    private val _isError = MutableStateFlow(false)
    private val _isLoading = MutableStateFlow(false)

    val uiState = combine(
        _isError,
        _isLoading
    ){ isError,isLoading->
        HttpUiState(
            isError = isError,
            isLoading = isLoading
        )
    }.stateInTimeout(viewModelScope, HttpUiState())

    fun getListUsers(){
        if(_isLoading.value){
            return
        }

        viewModelScope.launch {
            httpRepository.getListUsers(1).collect{
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
                        Log.debug("tag_create","error")
                        _isLoading.value = false
                        _isError.value = true
                    }
                    Result.Loading -> {
                        Log.debug("tag_create","loading")
                        _isLoading.value = true
                    }
                    is Result.Success -> {
                        Log.debug("tag_create","Success")
                        _isLoading.value = false
                    }
                }
            }
        }
    }
}
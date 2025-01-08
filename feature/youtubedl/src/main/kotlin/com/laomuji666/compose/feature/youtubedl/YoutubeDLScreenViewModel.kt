package com.laomuji666.compose.feature.youtubedl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laomuji666.compose.core.ui.stateInTimeout
import com.laomuji666.compose.feature.youtubedl.model.DownloadInfo
import com.laomuji666.compose.feature.youtubedl.model.YoutubeDLService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class YoutubeDLScreenViewModel @Inject constructor(
    private val youtubeDLService: YoutubeDLService
) : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    private val _url = MutableStateFlow("")
    private val _downloadInfo = youtubeDLService.getDownloadInfoList()

    val uiState = combine(
        _isLoading,
        _url,
        _downloadInfo
    ){ isLoading, url, downloadInfo ->
        YoutubeDLScreenUiState(
            isLoading = isLoading,
            url = url,
            downloadInfo = downloadInfo
        )
    }.stateInTimeout(viewModelScope, YoutubeDLScreenUiState())

    fun onAction(action: YoutubeDLScreenAction){
        when(action){
            is YoutubeDLScreenAction.SetUrl -> _url.value = action.url
            YoutubeDLScreenAction.OnDownloadVideoClick -> downloadVideo()
            is YoutubeDLScreenAction.SwitchDownloadVideo -> switchDownloadVideo(action.downloadInfo)
        }
    }

    private fun downloadVideo(){
        _isLoading.value = true
        youtubeDLService.downloadVideo(url = _url.value){
            _isLoading.value = false
            _url.value = ""
        }
    }

    private fun switchDownloadVideo(downloadInfo: DownloadInfo){
        _isLoading.value = true
        youtubeDLService.switchDownloadVideo(downloadInfo){
            _isLoading.value = false
        }
    }
}
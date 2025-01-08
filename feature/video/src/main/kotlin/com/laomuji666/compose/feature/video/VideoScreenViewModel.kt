package com.laomuji666.compose.feature.video

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoScreenViewModel @Inject constructor() : ViewModel() {
    private val _playerState = MutableStateFlow<ExoPlayer?>(null)
    val uiState: StateFlow<ExoPlayer?> = _playerState

    private var currentPosition: Long = 0L

    fun initializePlayer(context: Context, videoUri: String) {
        if (_playerState.value == null) {
            viewModelScope.launch {
                val exoPlayer = ExoPlayer.Builder(context).build().also {
                    val mediaItem = MediaItem.fromUri(Uri.parse(videoUri))
                    it.setMediaItem(mediaItem)
                    it.prepare()
                    it.playWhenReady = true
                    it.seekTo(currentPosition)
                    it.addListener(object : Player.Listener {
                        override fun onPlayerError(error: PlaybackException) {
                            //TODO 处理视频播放的异常
                        }
                    })
                }
                _playerState.value = exoPlayer
            }
        }
    }

    fun savePlayerState() {
        _playerState.value?.let {
            currentPosition = it.currentPosition
        }
    }

    fun releasePlayer() {
        _playerState.value?.release()
        _playerState.value = null
    }
}
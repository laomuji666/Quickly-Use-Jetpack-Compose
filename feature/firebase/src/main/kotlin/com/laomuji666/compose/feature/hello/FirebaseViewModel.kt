package com.laomuji666.compose.feature.hello

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laomuji666.compose.core.logic.analytics.Analytics
import com.laomuji666.compose.core.logic.notification.Notification
import com.laomuji666.compose.core.ui.stateInTimeout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(
    private val analytics: Analytics,
    private val notification: Notification
) : ViewModel() {
    private val _pushToken = MutableStateFlow("")
    val uiState = _pushToken.map {
        FirebaseUiState(
            pushToken = it
        )
    }.stateInTimeout(viewModelScope, FirebaseUiState())

    fun logEventClick(){
        analytics.logEvent("HelloScreen_logEventClick")
    }

    fun updatePushToken() {
        notification.updatePushToken {
            _pushToken.value = it
        }
    }
}
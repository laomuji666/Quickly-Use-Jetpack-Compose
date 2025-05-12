package com.laomuji888.compose.feature.chat.me

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laomuji888.compose.core.logic.common.cache.CacheUtil
import com.laomuji888.compose.core.logic.notification.NotificationHelper
import com.laomuji888.compose.core.ui.stateInTimeout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MeScreenViewModel @Inject constructor(
    cacheUtil: CacheUtil
) : ViewModel() {
    private var enableNotification by cacheUtil.cacheable(NotificationHelper.ENABLE_NOTIFICATION,false)
    private val _enableNotification = MutableStateFlow(enableNotification)

    val uiState = _enableNotification.map {
        MeScreenUiState(
            enableNotification = it
        )
    }.stateInTimeout(viewModelScope, MeScreenUiState())

    fun onAction(action: MeScreenAction){
        when(action){
            is MeScreenAction.SwitchEnableNotification -> switchEnableNotification()
        }
    }

    private fun switchEnableNotification(){
        val targetValue = !_enableNotification.value
        _enableNotification.value = targetValue
        enableNotification = targetValue
    }
}
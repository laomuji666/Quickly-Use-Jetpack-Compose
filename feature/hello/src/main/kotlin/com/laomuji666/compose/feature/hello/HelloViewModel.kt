package com.laomuji666.compose.feature.hello

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laomuji666.compose.core.logic.common.cache.CacheUtil
import com.laomuji666.compose.core.logic.location.Locator
import com.laomuji666.compose.core.ui.stateInTimeout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HelloViewModel @Inject constructor(
    cacheUtil: CacheUtil,
    private val locator: Locator
) : ViewModel() {
    private val _helloText = flow {
        emit("Compose")
    }

    private val _isLoading = MutableStateFlow(false)
    private var enableSwitchAppLogo by cacheUtil.cacheable("enableSwitchAppLogo",false)
    private val _enableSwitchAppLogo = MutableStateFlow(enableSwitchAppLogo)
    private val _location = MutableStateFlow("")

    val uiState = combine(
        _isLoading,
        _helloText,
        _enableSwitchAppLogo,
        _location
    ){ isLoading, helloText,enableSwitchAppLogo, location ->
        HelloUiState(
            isLoading = isLoading,
            helloText = helloText,
            enableSwitchAppLogo = enableSwitchAppLogo,
            location = location
        )
    }.stateInTimeout(viewModelScope, HelloUiState())

    fun switchAppLogo(context: Context){
        val targetValue = !_enableSwitchAppLogo.value
        _enableSwitchAppLogo.value = targetValue
        enableSwitchAppLogo = targetValue
        switchAliasActivity(context, targetValue)
    }

    private fun switchAliasActivity(context: Context, isDynamicAlias: Boolean){
        val defaultAliasCls = "com.laomuji666.compose.DefaultAliasActivity"
        val dynamicAliasCls = "com.laomuji666.compose.DynamicAliasActivity"

        val enableAliasActivity = ComponentName(context, if(isDynamicAlias) dynamicAliasCls else defaultAliasCls)
        context.packageManager.setComponentEnabledSetting(enableAliasActivity, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP)

        val disableAliasActivity = ComponentName(context, if(isDynamicAlias) defaultAliasCls else dynamicAliasCls)
        context.packageManager.setComponentEnabledSetting(disableAliasActivity, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP)
    }

    fun getLocation(context: Context){
        if(!locator.isEnableGps()){
            locator.openGpsSetting(context)
            return
        }
        viewModelScope.launch {
            _isLoading.value = true
            val currentLocation = locator.getCurrentLocation()
            if(currentLocation == null){
                _location.value = "error"
            }else{
                _location.value = "${currentLocation.latitude}\n${currentLocation.longitude}"
            }
            _isLoading.value = false
        }
    }
}
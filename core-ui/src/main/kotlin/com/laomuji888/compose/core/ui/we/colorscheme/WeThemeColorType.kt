package com.laomuji888.compose.core.ui.we.colorscheme

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed class WeThemeColorType {
    data object FlowSystem : WeThemeColorType()
    data object Light : WeThemeColorType()
    data object Dark : WeThemeColorType()
    data object Blue : WeThemeColorType()

    companion object {
        private val _currentWeThemeColorType = MutableStateFlow<WeThemeColorType>(FlowSystem)

        val currentWeThemeColorType = _currentWeThemeColorType.asStateFlow()

        fun setWeThemeColorType(weThemeColorType: WeThemeColorType) {
            _currentWeThemeColorType.update {
                weThemeColorType
            }
        }

        fun setWeThemeColorType(clsName: String?) {
            val weThemeColorType = when (clsName) {
                null -> FlowSystem
                FlowSystem::class.java.name -> FlowSystem
                Light::class.java.name -> Light
                Dark::class.java.name -> Dark
                Blue::class.java.name -> Blue
                else -> FlowSystem
            }
            setWeThemeColorType(weThemeColorType)
        }
    }
}
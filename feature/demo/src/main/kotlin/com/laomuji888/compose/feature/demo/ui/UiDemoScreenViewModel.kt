package com.laomuji888.compose.feature.demo.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.laomuji888.compose.core.ui.stateInTimeout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class UiDemoScreenViewModel @Inject constructor() : ViewModel() {
    private val _dragList = MutableStateFlow(listOf("1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30"))
    val uiState = _dragList.map {
        UiDemoScreenUiState(
            dragList = it
        )
    }.stateInTimeout(viewModelScope, UiDemoScreenUiState())

    fun onAction(action: UiDemoScreenAction){
        when(action){
            is UiDemoScreenAction.SwapDragList -> {
                swapDragList(action.a,action.b)
            }
        }
    }

    private fun swapDragList(a:Int,b:Int){
        val oldList = _dragList.value
        val newList = oldList.toMutableList()
        val oldA = oldList[a]
        val oldB = oldList[b]
        newList[a] = oldB
        newList[b] = oldA
        _dragList.value = newList
    }
}
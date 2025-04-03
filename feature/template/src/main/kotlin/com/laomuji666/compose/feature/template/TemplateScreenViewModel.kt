package com.laomuji666.compose.feature.template

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.laomuji666.compose.core.ui.stateInTimeout
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@HiltViewModel
class TemplateScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val route = (savedStateHandle.toRoute<TemplateScreenRoute>())
    private val _title = MutableStateFlow(route.title)
    private val _text = MutableStateFlow(route.text)

    val uiState = combine(
        _title,
        _text,
    ){ title, text ->
        TemplateScreenUiState(
            title = title,
            text = text,
        )
    }.stateInTimeout(viewModelScope, TemplateScreenUiState())

    fun onAction(
        action: TemplateScreenAction,
        onButtonClick: (String) -> Unit,
    ){
        when(action) {
            is TemplateScreenAction.OnTextChanged -> {
                _text.value = action.text
            }
            TemplateScreenAction.OnButtonClicked -> {
                onButtonClick(_text.value)
            }
        }
    }
}
package com.laomuji888.compose.feature.template

sealed interface TemplateScreenAction {
    data class OnTextChanged(val text: String) : TemplateScreenAction
    data object OnButtonClicked : TemplateScreenAction
}
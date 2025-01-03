package com.laomuji666.compose.feature.hello

sealed interface HttpScreenAction {
    data object GetListUsers : HttpScreenAction
    data object CreateUser : HttpScreenAction
}
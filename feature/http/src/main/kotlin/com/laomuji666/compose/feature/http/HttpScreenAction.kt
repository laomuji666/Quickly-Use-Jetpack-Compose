package com.laomuji666.compose.feature.http

sealed interface HttpScreenAction {
    data object GetListUsers : HttpScreenAction
    data object CreateUser : HttpScreenAction
}
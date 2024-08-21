package com.laomuji666.compose.core.logic.http.request

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserRequest(
    val name:String,
    val job:String
)
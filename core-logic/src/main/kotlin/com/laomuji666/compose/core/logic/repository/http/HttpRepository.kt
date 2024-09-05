package com.laomuji666.compose.core.logic.repository.http

import com.laomuji666.compose.core.logic.repository.http.request.CreateUserRequest
import com.laomuji666.compose.core.logic.repository.http.response.CreateUserResponse
import com.laomuji666.compose.core.logic.repository.http.response.UserInfoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class HttpRepository(
    private val client: HttpClient
) {
    fun delayRequest() = flow {
        val response = client.get("https://reqres.in/api/users?delay=3") {}
        val body: UserInfoResponse = response.body()
        emit(Json.Default.encodeToString(body))
    }.asResult()

    fun createUser(request: CreateUserRequest) = flow {
        val response = client.post("https://reqres.in/api/users") {
            setBody(request)
            contentType(ContentType.Application.Json)
        }
        val body: CreateUserResponse = response.body()
        emit(Json.Default.encodeToString(body))
    }.asResult()
}
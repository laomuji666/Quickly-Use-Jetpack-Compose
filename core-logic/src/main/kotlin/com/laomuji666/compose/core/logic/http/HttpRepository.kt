package com.laomuji666.compose.core.logic.http

import com.laomuji666.compose.core.logic.http.request.CreateUserRequest
import com.laomuji666.compose.core.logic.http.response.CreateUserResponse
import com.laomuji666.compose.core.logic.http.response.UserInfoResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.flow.flow

class HttpRepository(
    private val client: HttpClient
) {
    fun getListUsers(page:Int) = flow {
        val response = client.get("https://reqres.in/api/users?page=$page") {}
        val body:UserInfoResponse = response.body()
        emit(body)
    }.asResult()

    fun createUser(request:CreateUserRequest) = flow {
        val response = client.post("https://reqres.in/api/users") {
            setBody(request)
            contentType(ContentType.Application.Json)
        }
        val body: CreateUserResponse = response.body()
        emit(body)
    }.asResult()
}
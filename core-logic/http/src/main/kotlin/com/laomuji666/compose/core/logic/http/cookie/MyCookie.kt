package com.laomuji666.compose.core.logic.http.cookie

import io.ktor.http.Cookie
import io.ktor.http.CookieEncoding
import kotlinx.serialization.Serializable

@Serializable
data class MyCookie(
    val name: String,
    val value: String,
    val encoding: CookieEncoding = CookieEncoding.URI_ENCODING,
    val maxAge: Int = 0,
    val domain: String? = null,
    val path: String? = null,
    val secure: Boolean = false,
    val httpOnly: Boolean = false,
    val extensions: Map<String, String?> = emptyMap()
){
    constructor(cookie: Cookie) : this(
        cookie.name,
        cookie.value,
        cookie.encoding,
        cookie.maxAge,
        cookie.domain,
        cookie.path,
        cookie.secure,
        cookie.httpOnly,
        cookie.extensions
    )

    fun toCookie():Cookie{
        return Cookie(
            name = name,
            value = value,
            encoding = encoding,
            maxAge = maxAge,
            expires = null,
            domain = domain,
            path = path,
            secure = secure,
            httpOnly = httpOnly,
            extensions = extensions
        )
    }
}
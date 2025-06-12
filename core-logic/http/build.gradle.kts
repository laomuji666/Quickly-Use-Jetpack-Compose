plugins {
    alias(libs.plugins.laomuji1999.compose.library)
    alias(libs.plugins.laomuji1999.compose.hilt)
}

android {
    namespace = "com.laomuji1999.compose.core.logic.http"
}

dependencies {
    //common
    implementation(project(":core-logic:common"))

    //ktor 跨平台 核心
    implementation(libs.ktor.client.core)
    //ktor 日志
    implementation(libs.ktor.client.logging)
    //ktor 序列化
    implementation(libs.ktor.client.content.negotiation)
    //ktor 序列化 json
    implementation(libs.ktor.serialization.kotlinx.json)
    //ktor okhttp 引擎
    implementation(libs.ktor.client.okhttp)
}

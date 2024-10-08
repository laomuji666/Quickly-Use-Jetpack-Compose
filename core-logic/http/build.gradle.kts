plugins {
    alias(libs.plugins.laomuji666.compose.library)
    alias(libs.plugins.laomuji666.compose.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.laomuji666.compose.core.logic.http"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    //hilt
    ksp(libs.hilt.compiler)

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

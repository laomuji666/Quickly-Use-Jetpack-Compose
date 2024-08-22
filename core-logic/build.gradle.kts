plugins {
    alias(libs.plugins.laomuji666.compose.library)
    alias(libs.plugins.laomuji666.compose.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.laomuji666.compose.core.logic"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    //为Android提供kotlin扩展功能,view,context,collection,和其它扩展
    api(libs.androidx.core.ktx)
    //管理Android生命周期
    api(libs.androidx.lifecycle.runtime.ktx)

    api(libs.androidx.material3)
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)

    //compose的lifecycle扩展
    api(libs.androidx.lifecycle.runtime.compose)

    //hilt
    ksp(libs.hilt.compiler)

    //firebase
    api(platform(libs.firebase.bom))
    api(libs.firebase.analytics)
    api(libs.firebase.messaging)
    api(libs.firebase.crashlytics)

    //权限
    api(libs.accompanist.permissions)

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

    //谷歌认证
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
}

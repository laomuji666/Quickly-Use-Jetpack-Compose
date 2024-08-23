plugins {
    alias(libs.plugins.laomuji666.compose.library)
}

android {
    namespace = "com.laomuji666.compose.res"
}

dependencies {
    //启动屏幕
    api(libs.androidx.core.splashscreen)
}

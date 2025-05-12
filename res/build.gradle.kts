plugins {
    alias(libs.plugins.laomuji888.compose.library)
}

android {
    namespace = "com.laomuji888.compose.res"
}

dependencies {
    //启动屏幕
    api(libs.androidx.core.splashscreen)

    //app compat
    api(libs.androidx.appcompat)
}

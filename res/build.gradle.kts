plugins {
    alias(libs.plugins.laomuji1999.compose.library)
}

android {
    namespace = "com.laomuji1999.compose.res"
}

dependencies {
    //启动屏幕
    api(libs.androidx.core.splashscreen)

    //app compat
    api(libs.androidx.appcompat)
}

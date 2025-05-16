plugins {
    alias(libs.plugins.laomuji888.compose.library)
}

android {
    namespace = "com.laomuji888.compose.core.launcher"
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":core-logic:common"))

    //谷歌认证
    implementation(libs.play.services.auth)
}

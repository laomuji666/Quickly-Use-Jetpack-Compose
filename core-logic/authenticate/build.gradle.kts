plugins {
    alias(libs.plugins.laomuji1999.compose.library)
    alias(libs.plugins.laomuji1999.compose.hilt)
}

android {
    namespace = "com.laomuji1999.compose.core.logic.authenticate"
}

dependencies {

    //common
    implementation(project(":core-logic:common"))

    //谷歌认证
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)

    //BiometricManager, 生物识别
    implementation(libs.androidx.biometric)
}

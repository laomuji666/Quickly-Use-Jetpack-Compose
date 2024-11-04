plugins {
    alias(libs.plugins.laomuji666.compose.library)
    alias(libs.plugins.laomuji666.compose.hilt)
}

android {
    namespace = "com.laomuji666.compose.core.logic.authenticate"
}

dependencies {
    //hilt
    ksp(libs.hilt.compiler)

    //common
    implementation(project(":core-logic:common"))

    //谷歌认证
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)
}

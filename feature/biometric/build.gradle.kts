plugins {
    alias(libs.plugins.laomuji888.compose.library)
    alias(libs.plugins.laomuji888.compose.hilt)
}

android {
    namespace = "com.laomuji888.compose.feature.biometric"
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":core-logic:authenticate"))
}

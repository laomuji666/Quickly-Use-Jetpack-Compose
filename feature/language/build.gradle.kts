plugins {
    alias(libs.plugins.laomuji888.compose.library)
    alias(libs.plugins.laomuji888.compose.hilt)
}

android {
    namespace = "com.laomuji888.compose.feature.language"
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":core-logic:language"))
}

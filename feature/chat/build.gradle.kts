plugins {
    alias(libs.plugins.laomuji666.compose.library)
    alias(libs.plugins.laomuji666.compose.hilt)
}

android {
    namespace = "com.laomuji666.compose.feature.chat"
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":core-logic"))
    ksp(libs.hilt.compiler)
}

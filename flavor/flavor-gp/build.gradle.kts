plugins {
    alias(libs.plugins.laomuji666.compose.library)
    alias(libs.plugins.laomuji666.compose.hilt)
}

android {
    namespace = "com.laomuji666.compose.flavor.gp"
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":res"))
}

plugins {
    alias(libs.plugins.laomuji888.compose.library)
    alias(libs.plugins.laomuji888.compose.hilt)
}

android {
    namespace = "com.laomuji888.compose.feature.video"
}

dependencies {
    implementation(project(":core-ui"))

    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.ui)

    implementation(libs.androidx.documentfile)
}

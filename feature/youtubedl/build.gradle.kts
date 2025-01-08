plugins {
    alias(libs.plugins.laomuji666.compose.library)
    alias(libs.plugins.laomuji666.compose.hilt)
}

android {
    namespace = "com.laomuji666.compose.feature.youtubedl"
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":res"))
    implementation(project(":core-logic:database"))
    implementation(project(":feature:video"))

    implementation(libs.youtubedl.android.library)
    implementation(libs.youtubedl.android.ffmpeg)
    implementation(libs.youtubedl.android.aria2c)
}

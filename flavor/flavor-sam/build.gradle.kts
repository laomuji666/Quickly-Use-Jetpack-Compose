plugins {
    alias(libs.plugins.laomuji1999.compose.library)
    alias(libs.plugins.laomuji1999.compose.hilt)
}

android {
    namespace = "com.laomuji1999.compose.flavor.sam"
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":res"))
}

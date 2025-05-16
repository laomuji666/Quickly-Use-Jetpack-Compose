plugins {
    alias(libs.plugins.laomuji888.compose.library)
    alias(libs.plugins.laomuji888.compose.hilt)
}

android {
    namespace = "com.laomuji888.compose.flavor.sam"
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":res"))
}

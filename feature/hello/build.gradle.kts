plugins {
    alias(libs.plugins.laomuji666.compose.library)
    alias(libs.plugins.laomuji666.compose.hilt)
}

android {
    namespace = "com.laomuji666.compose.feature.hello"
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":core-logic:common"))
    implementation(project(":core-logic:authenticate"))
    implementation(project(":core-logic:location"))
}

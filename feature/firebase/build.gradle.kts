plugins {
    alias(libs.plugins.laomuji1999.compose.library)
    alias(libs.plugins.laomuji1999.compose.hilt)
}

android {
    namespace = "com.laomuji1999.compose.feature.firebase"
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":core-logic:common"))
    implementation(project(":core-logic:analytics"))
    implementation(project(":core-logic:notification"))
    implementation(project(":core-launcher"))
}

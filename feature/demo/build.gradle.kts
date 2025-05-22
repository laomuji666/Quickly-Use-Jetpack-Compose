plugins {
    alias(libs.plugins.laomuji888.compose.library)
    alias(libs.plugins.laomuji888.compose.hilt)
}

android {
    namespace = "com.laomuji888.compose.feature.main"
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":core-logic:common"))
    implementation(project(":core-logic:authenticate"))
    implementation(project(":core-logic:location"))
    implementation(project(":core-launcher"))
}

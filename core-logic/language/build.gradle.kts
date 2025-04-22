plugins {
    alias(libs.plugins.laomuji666.compose.library)
    alias(libs.plugins.laomuji666.compose.hilt)
}

android {
    namespace = "com.laomuji666.compose.core.logic.language"
}

dependencies {
    //common
    implementation(project(":core-logic:common"))
    //res
    implementation(project(":res"))
}

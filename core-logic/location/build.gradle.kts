plugins {
    alias(libs.plugins.laomuji666.compose.library)
    alias(libs.plugins.laomuji666.compose.hilt)
}

android {
    namespace = "com.laomuji666.compose.core.logic.location"
}

dependencies {
    //common
    implementation(project(":core-logic:common"))

    //location
    implementation(libs.play.services.location)
}

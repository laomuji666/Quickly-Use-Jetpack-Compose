plugins {
    alias(libs.plugins.laomuji1999.compose.library)
    alias(libs.plugins.laomuji1999.compose.hilt)
}

android {
    namespace = "com.laomuji1999.compose.core.logic.location"
}

dependencies {
    //common
    implementation(project(":core-logic:common"))

    //location
    implementation(libs.play.services.location)
}

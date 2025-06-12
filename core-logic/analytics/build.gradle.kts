plugins {
    alias(libs.plugins.laomuji1999.compose.library)
    alias(libs.plugins.laomuji1999.compose.hilt)
}

android {
    namespace = "com.laomuji1999.compose.core.logic.analytics"
}

dependencies {
    //common
    implementation(project(":core-logic:common"))

    //firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}

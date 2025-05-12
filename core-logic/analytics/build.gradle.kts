plugins {
    alias(libs.plugins.laomuji888.compose.library)
    alias(libs.plugins.laomuji888.compose.hilt)
}

android {
    namespace = "com.laomuji888.compose.core.logic.analytics"
}

dependencies {
    //common
    implementation(project(":core-logic:common"))

    //firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
}

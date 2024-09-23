plugins {
    alias(libs.plugins.laomuji666.compose.library)
    alias(libs.plugins.laomuji666.compose.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.laomuji666.compose.core.logic.notification"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    //hilt
    ksp(libs.hilt.compiler)

    //common
    implementation(project(":core-logic:common"))

    //firebase
    implementation(platform(libs.firebase.bom))
    api(libs.firebase.messaging)
}

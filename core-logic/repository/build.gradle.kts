plugins {
    alias(libs.plugins.laomuji666.compose.library)
    alias(libs.plugins.laomuji666.compose.hilt)
}

android {
    namespace = "com.laomuji666.compose.core.logic.repository"
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    //hilt
    ksp(libs.hilt.compiler)

    //project
    implementation(project(":core-logic:common"))
    implementation(project(":core-ui"))
    api(project(":core-logic:database"))
    implementation(project(":core-logic:notification"))

    //firebase
    implementation(platform(libs.firebase.bom))

    //google ai
    implementation(libs.generativeai)
}

plugins {
    alias(libs.plugins.laomuji666.compose.library)
    alias(libs.plugins.laomuji666.compose.hilt)
    alias(libs.plugins.kotlin.serialization)
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
    implementation(libs.firebase.database)
    implementation(libs.firebase.storage)

    //google ai
    implementation(libs.generativeai)
}

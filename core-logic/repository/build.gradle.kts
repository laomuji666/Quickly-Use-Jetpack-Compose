plugins {
    alias(libs.plugins.laomuji888.compose.library)
    alias(libs.plugins.laomuji888.compose.hilt)
}

android {
    namespace = "com.laomuji888.compose.core.logic.repository"
}

dependencies {

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

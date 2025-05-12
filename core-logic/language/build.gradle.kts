plugins {
    alias(libs.plugins.laomuji888.compose.library)
    alias(libs.plugins.laomuji888.compose.hilt)
}

android {
    namespace = "com.laomuji888.compose.core.logic.language"
}

dependencies {
    //common
    implementation(project(":core-logic:common"))
    //res
    implementation(project(":res"))
}

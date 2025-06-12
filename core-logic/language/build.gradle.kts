plugins {
    alias(libs.plugins.laomuji1999.compose.library)
    alias(libs.plugins.laomuji1999.compose.hilt)
}

android {
    namespace = "com.laomuji1999.compose.core.logic.language"
}

dependencies {
    //common
    implementation(project(":core-logic:common"))
    //res
    implementation(project(":res"))
}

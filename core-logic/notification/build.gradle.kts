plugins {
    alias(libs.plugins.laomuji888.compose.library)
    alias(libs.plugins.laomuji888.compose.hilt)
}

android {
    namespace = "com.laomuji888.compose.core.logic.notification"
}

dependencies {
    //res
    implementation(project(":res"))

    //common
    implementation(project(":core-logic:common"))

    //database
    implementation(project(":core-logic:database"))

    //firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.messaging)
}

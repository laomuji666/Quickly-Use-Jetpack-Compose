plugins {
    alias(libs.plugins.laomuji1999.compose.library)
    alias(libs.plugins.laomuji1999.compose.hilt)
}

android {
    namespace = "com.laomuji1999.compose.core.logic.notification"
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

plugins {
    alias(libs.plugins.laomuji888.compose.library)
    alias(libs.plugins.laomuji888.compose.hilt)
}

android {
    namespace = "com.laomuji888.compose.core.logic.database"
    defaultConfig {
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {

    //database
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
}

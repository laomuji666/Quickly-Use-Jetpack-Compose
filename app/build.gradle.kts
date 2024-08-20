plugins {
    alias(libs.plugins.laomuji666.compose.application)
    alias(libs.plugins.laomuji666.compose.hilt)
}

//其它AndroidApplication相关的配置在[ApplicationConventionPlugin]
android {
    //命名空间,尽量和applicationId一致,涉及到一些文件的路径.
    namespace = "com.laomuji666.compose.quickly"

    //默认配置
    defaultConfig {
        applicationId = "com.laomuji666.compose.quickly"
        versionCode = 1
        versionName = "1.0"
    }
}

dependencies {
    //基础库
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.lifecycle.runtime.compose)

    //hilt
    ksp(libs.hilt.compiler)

    //启动屏幕
    implementation(libs.androidx.core.splashscreen)
}
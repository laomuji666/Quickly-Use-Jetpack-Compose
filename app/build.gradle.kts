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
    //关联view和compose
    implementation(libs.androidx.activity.compose)
    //hilt
    ksp(libs.hilt.compiler)
    //启动屏幕
    implementation(libs.androidx.core.splashscreen)

    //其它module
    implementation(project(":core-ui"))
    implementation(project(":core-logic"))
    implementation(project(":feature:hello"))


}
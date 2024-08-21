plugins {
    alias(libs.plugins.laomuji666.compose.library)
    alias(libs.plugins.laomuji666.compose.hilt)
}

android {
    namespace = "com.laomuji666.compose.core.logic"
}

dependencies {
    //为Android提供kotlin扩展功能,view,context,collection,和其它扩展
    api(libs.androidx.core.ktx)
    //管理Android生命周期
    api(libs.androidx.lifecycle.runtime.ktx)

    api(libs.androidx.material3)
    api(platform(libs.androidx.compose.bom))
    api(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui)
    api(libs.androidx.compose.ui.graphics)

    //compose的lifecycle扩展
    api(libs.androidx.lifecycle.runtime.compose)

    //hilt
    ksp(libs.hilt.compiler)

    //firebase
    api(platform(libs.firebase.bom))
    api(libs.firebase.analytics)
    api(libs.firebase.messaging)
    api(libs.firebase.crashlytics)

    //权限
    api(libs.accompanist.permissions)
}

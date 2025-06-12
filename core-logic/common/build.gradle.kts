plugins {
    alias(libs.plugins.laomuji1999.compose.library)
    alias(libs.plugins.laomuji1999.compose.hilt)
}

android {
    namespace = "com.laomuji1999.compose.core.logic.common"
    buildTypes {
        debug {
            buildConfigField("String","WEB_CLIENT_ID","\"954472126977-chps0hidiamvrln1ls96hqp4lgq14co6.apps.googleusercontent.com\"")
            buildConfigField("String","GEMINI_API_KEY","\"AIzaSyCuM1ecXRu37ZFy_DIQlIQWC9fkzkljKzg\"")
        }
        release {
            buildConfigField("String","WEB_CLIENT_ID","\"954472126977-chps0hidiamvrln1ls96hqp4lgq14co6.apps.googleusercontent.com\"")
            buildConfigField("String","GEMINI_API_KEY","\"AIzaSyCuM1ecXRu37ZFy_DIQlIQWC9fkzkljKzg\"")
        }
    }
}

dependencies {

}

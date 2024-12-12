plugins {
    alias(libs.plugins.laomuji666.compose.library)
    alias(libs.plugins.laomuji666.compose.hilt)
}

android {
    namespace = "com.laomuji666.compose.core.logic.common"
    buildTypes {
        debug {
            buildConfigField("String","WEB_CLIENT_ID","\"905105202439-5v3lh96r8pgo1ubuv2qme1h6vahn4imi.apps.googleusercontent.com\"")
            buildConfigField("String","GEMINI_API_KEY","\"AIzaSyAwC28Brrujfj43BOt6QKhp2U4bR5PbLgI\"")
        }
        release {
            buildConfigField("String","WEB_CLIENT_ID","\"905105202439-5v3lh96r8pgo1ubuv2qme1h6vahn4imi.apps.googleusercontent.com\"")
            buildConfigField("String","GEMINI_API_KEY","\"AIzaSyAwC28Brrujfj43BOt6QKhp2U4bR5PbLgI\"")
        }
    }
}

dependencies {

}

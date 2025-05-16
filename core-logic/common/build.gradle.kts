plugins {
    alias(libs.plugins.laomuji888.compose.library)
    alias(libs.plugins.laomuji888.compose.hilt)
}

android {
    namespace = "com.laomuji888.compose.core.logic.common"
    buildTypes {
        debug {
            buildConfigField("String","WEB_CLIENT_ID","\"905105202439-5v3lh96r8pgo1ubuv2qme1h6vahn4imi.apps.googleusercontent.com\"")
            buildConfigField("String","GEMINI_API_KEY","\"AIzaSyCV9WR4es6zWWWDBmNKLF95hwWJpqIVhcE\"")
        }
        release {
            buildConfigField("String","WEB_CLIENT_ID","\"905105202439-5v3lh96r8pgo1ubuv2qme1h6vahn4imi.apps.googleusercontent.com\"")
            buildConfigField("String","GEMINI_API_KEY","\"AIzaSyCV9WR4es6zWWWDBmNKLF95hwWJpqIVhcE\"")
        }
    }
}

dependencies {

}

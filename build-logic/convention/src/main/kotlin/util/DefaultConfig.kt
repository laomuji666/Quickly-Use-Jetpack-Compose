package util

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion

fun ApplicationExtension.applicationDefaultConfig(){
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    compileSdk = DefaultConfigConstant.COMPILE_SDK
    defaultConfig {
        minSdk = DefaultConfigConstant.MIN_SDK
        targetSdk = DefaultConfigConstant.TARGET_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

fun LibraryExtension.libraryDefaultConfig(){
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    compileSdk = DefaultConfigConstant.COMPILE_SDK
    defaultConfig {
        minSdk = DefaultConfigConstant.MIN_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
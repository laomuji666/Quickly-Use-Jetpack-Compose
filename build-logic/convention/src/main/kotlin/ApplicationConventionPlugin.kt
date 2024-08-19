import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class ApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            //Application需要的插件
            applicationPlugins()

            extensions.configure<ApplicationExtension> {
                //使用JDK17
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }

                //application默认配置
                applicationDefaultConfig()

                //打包配置
                buildConfig(target)
            }
        }
    }

    private fun Project.applicationPlugins(){
        with(pluginManager){
            apply("com.android.application")
            apply("org.jetbrains.kotlin.android")
            apply("org.jetbrains.kotlin.plugin.compose")
        }
    }

    private fun ApplicationExtension.applicationDefaultConfig(){
        //构建sdk版本
        compileSdk = 34
        defaultConfig {
            //最低支持sdk版本
            minSdk = 24
            //目标sdk版本,尽量和compileSdk版本一致
            targetSdk = compileSdk

            //测试InstrumentationRunner
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            //开启vectorDrawables支持
            vectorDrawables {
                useSupportLibrary = true
            }
        }
    }

    private fun ApplicationExtension.buildConfig(project: Project){
        //构建功能
        buildFeatures {
            //开启compose支持
            compose = true
            //开启buildConfig支持
            buildConfig = true
        }

        //打包配置
        packaging {
            //构建资源文件
            resources {
                //排除文件路径,该路径的文件不会被打包.
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }

        //打包签名文件
        signingConfigs {
            getByName("debug") {
                storeFile = project.file("../keystore/key.jks")
                keyAlias = "laomuji"
                storePassword = "laomuji666"
                keyPassword = "laomuji666"
            }
            create("release") {
                storeFile = project.file("../keystore/key.jks")
                keyAlias = "laomuji"
                storePassword = "laomuji666"
                keyPassword = "laomuji666"
            }
        }
        //打包配置
        buildTypes {
            debug {
                signingConfig = signingConfigs.getByName("debug")
                isMinifyEnabled = false
            }
            release {
                signingConfig = signingConfigs.getByName("release")
                isMinifyEnabled = true
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
    }
}
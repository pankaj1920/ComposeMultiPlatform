import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    id("dev.icerock.mobile.multiplatform-resources")
//    id("org.jetbrains.kotlin.android")
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            export("dev.icerock.moko:resources:0.23.0")
            export("dev.icerock.moko:graphics:0.9.0") // toUIColor here
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(libs.compose.ui)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)
            api("dev.icerock.moko:resources:0.23.0")
            api("dev.icerock.moko:resources-compose:0.23.0") // for compose multiplatform
            // Navigator
            implementation("cafe.adriel.voyager:voyager-navigator:1.0.0-rc10")

            // BottomSheetNavigator
            implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:1.0.0-rc10")

            // TabNavigator
            implementation("cafe.adriel.voyager:voyager-tab-navigator:1.0.0-rc10")

            // Transitions
            implementation("cafe.adriel.voyager:voyager-transitions:1.0.0-rc10")
        }
    }
}

android {
    namespace = "com.app.composemultiplatform"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets {
        getByName("main").java.srcDirs("build/generated/moko/androidMain/src")
    }

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")



    defaultConfig {
        applicationId = "com.app.composemultiplatform"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}
dependencies {
    implementation("androidx.core:core-ktx:+")
}

multiplatformResources {
    multiplatformResourcesPackage = "com.app.composemultiplatform" // required
    multiplatformResourcesClassName = "SharedResource" // optional, default MR

}

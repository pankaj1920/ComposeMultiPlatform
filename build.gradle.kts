plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
}

buildscript {
//    val kotlin_version by extra("1.9.20")
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }

    dependencies {
        classpath("dev.icerock.moko:resources-generator:0.23.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20")
    }
}
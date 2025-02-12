plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.splunk.test"

    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":theme"))

    androidX()
    compose()
    hilt()
}
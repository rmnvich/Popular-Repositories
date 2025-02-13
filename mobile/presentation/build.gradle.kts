plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.splunk.test.mobile.presentation"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    implementation(project(":core:utils"))
    implementation(project(":mobile:domain"))
    implementation(project(":mobile:theme"))

    androidX()
    compose()
    paging()
    hilt()

    implementation(libs.compose.coil)
}
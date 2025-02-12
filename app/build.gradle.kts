plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.splunk.test"
}

dependencies {
    implementation(project(":mobile:presentation"))
    implementation(project(":mobile:data"))

    hilt()
}
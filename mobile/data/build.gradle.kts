plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.splunk.test.mobile.data"
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":mobile:domain"))

    implementation(libs.retrofit)
    moshi()
    hilt()
}
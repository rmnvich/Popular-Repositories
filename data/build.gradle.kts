plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.splunk.test.data"
}


dependencies {
    implementation(project(":domain"))
    implementation(project(":network"))

    retrofit()
    moshi()
    hilt()
}
plugins {
    alias(libs.plugins.ksp)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.splunk.test.core.network"

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        // ⚠️ WARNING: In a real-world application, it's recommended to store the BASE_URL securely,
        // either in NDK or remote configuration.
        // For simplicity, it's hardcoded here since this is a public API.
        buildConfigField("String", "BASE_URL", "\"https://api.github.com\"")

        // ⚠️ WARNING: Never store sensitive tokens like this in a production environment.
        // This is only hardcoded to simplify the review process.
        // In a real application, consider using GitHub Actions Secrets or remote configuration.
        buildConfigField("String", "GITHUB_TOKEN", "\"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\"")
    }
}


dependencies {
    retrofit()
    okhttp()
    moshi()
    hilt()
}
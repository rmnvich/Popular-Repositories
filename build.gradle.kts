import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompilerOptions
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.hilt) apply false
}

subprojects {
    plugins.withId("com.android.application") {
        configure<BaseAppModuleExtension> {
            compileSdk = 34
            defaultConfig {
                versionCode = 1
                versionName = "1.0.0"
            }
            defaultConfig {
                minSdk = 26
                targetSdk = 34

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables {
                    useSupportLibrary = true
                }
            }
            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
        }

        tasks.withType<KotlinCompilationTask<*>>().configureEach {
            val options = compilerOptions as KotlinJvmCompilerOptions
            options.jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }
}
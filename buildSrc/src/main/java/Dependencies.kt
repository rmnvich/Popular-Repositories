import org.gradle.accessors.dm.LibrariesForLibs
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

private val Project.libs: LibrariesForLibs
    get() = extensions.getByType<LibrariesForLibs>()

fun Project.androidX() {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.appcompat)
}

fun Project.compose() {
    implementation(libs.compose.bom)
    implementation(libs.compose.ui)
    implementation(libs.compose.activity)
    implementation(libs.compose.viewmodel)
    implementation(libs.compose.lifecycle)
    implementation(libs.compose.material3)
    implementation(libs.compose.navigation)
    implementation(libs.compose.coil)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)
}

fun Project.hilt() {
    implementation(libs.dagger.hilt)
    implementation(libs.dagger.hilt.navigation.compose)
    ksp(libs.dagger.hilt.compiler)
}

fun Project.okhttp() {
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
}

fun Project.moshi() {
    implementation(libs.moshi)
    implementation(libs.moshi.kotlin)
    implementation(libs.moshi.converter)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
}

fun Project.paging() {
    implementation(libs.paging.runtime)
    implementation(libs.paging.compose)
}
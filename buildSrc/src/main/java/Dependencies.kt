import org.gradle.api.Project

fun Project.androidX() {
    implementation("androidx-core-ktx")
    implementation("androidx-lifecycle-runtime-ktx")
    implementation("androidx-appcompat")
    implementation("material")
}

fun Project.compose() {
    implementation("compose-bom")
    implementation("compose-ui")
    implementation("compose-activity")
    implementation("compose-viewmodel")
    implementation("compose-lifecycle")
    implementation("compose-material3")
    implementation("compose-navigation")
    implementation("compose-ui-graphics")
    implementation("compose-ui-tooling-preview")
    debugImplementation("compose-ui-tooling")
}

fun Project.hilt() {
    implementation("dagger-hilt")
    implementation("dagger-hilt-navigation-compose")
    ksp("dagger-hilt-compiler")
}
import org.gradle.api.Project

fun Project.implementation(dependencyNotation: Any) {
    addDependency("implementation", dependencyNotation)
}

fun Project.debugImplementation(dependencyNotation: Any) {
    addDependency("debugImplementation", dependencyNotation)
}

fun Project.ksp(dependencyNotation: Any) {
    addDependency("ksp", dependencyNotation)
}

private fun Project.addDependency(configuration: String, dependencyNotation: Any) {
    dependencies.add(configuration, dependencyNotation)
}
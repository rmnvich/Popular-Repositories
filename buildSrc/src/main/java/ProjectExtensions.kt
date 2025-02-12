import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension

private val Project.libs: VersionCatalog
    get() = extensions.getByType(VersionCatalogsExtension::class.java).named("libs")

fun Project.implementation(alias: String) {
    addDependency("implementation", alias)
}

fun Project.ksp(alias: String) {
    addDependency("ksp", alias)
}

fun Project.debugImplementation(alias: String) {
    addDependency("debugImplementation", alias)
}

private fun Project.addDependency(configuration: String, alias: String) {
    dependencies.add(configuration, libs.findLibrary(alias).get())
}
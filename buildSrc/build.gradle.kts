plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    val path = libs.javaClass.superclass.protectionDomain.codeSource.location
    implementation(files(path))
}
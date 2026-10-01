plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "com.ejemplo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")

}

tasks.test {
    useJUnitPlatform()
}
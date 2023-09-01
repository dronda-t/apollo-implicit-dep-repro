import com.example.plugin.TestCopyTask

plugins {
    kotlin("jvm")
    id("com.apollographql.apollo3") version "3.8.2"
    id("com.example.plugin.test")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.apollographql.apollo3:apollo-api:3.8.2")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

val testCopy by tasks.creating(TestCopyTask::class.java) {
    schemaFile.set(file("schema/test.graphql"))
    outputDirectory.set(file("$buildDir/newSchema"))
}

apollo {
    service("test-service") {
        packageName.set("com.example.test")
        schemaFile.set(testCopy.outputDirectory.file("test.graphql"))
    }
}


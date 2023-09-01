plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("gradle-plugin", "1.9.0"))
}

gradlePlugin {
    plugins {
        register("TestCopy") {
            id = "com.example.plugin.test"
            implementationClass = "com.example.plugin.TestCopy"
        }
    }
}

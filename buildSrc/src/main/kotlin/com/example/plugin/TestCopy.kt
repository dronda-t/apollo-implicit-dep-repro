package com.example.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.copyTo
import kotlin.io.path.createDirectories
import kotlin.io.path.deleteRecursively

open class TestCopyTask : DefaultTask() {
    @Input
    val schemaFile: Property<File> = project.objects.property(File::class.java)
    @OutputDirectory
    val outputDirectory: DirectoryProperty = project.objects.directoryProperty()

    @OptIn(ExperimentalPathApi::class)
    @TaskAction
    fun doWork() {
        dependsOn("processResources")
        val outputDir = outputDirectory.get().asFile.toPath()
        outputDir.deleteRecursively()
        outputDir.createDirectories()

        schemaFile.get().toPath().copyTo(outputDir.resolve(schemaFile.get().name))
    }
}

class TestCopy : Plugin<Project> {
    override fun apply(target: Project) {}
}





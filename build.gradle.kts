// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.2.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.7.0-RC2")
    }
}

plugins {
    id("io.gitlab.arturbosch.detekt")
    id("org.jlleitschuh.gradle.ktlint")
}

subprojects {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    apply("../buildscripts/ktlint.gradle")
    apply("../buildscripts/detekt.gradle")

    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile).configureEach {
        kotlinOptions {
            // Treat all Kotlin warnings as errors
//            allWarningsAsErrors = true

            freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"

            // Enable experimental coroutines APIs, including Flow
            freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
            freeCompilerArgs += "-Xopt-in=kotlinx.coroutines.FlowPreview"
            freeCompilerArgs += "-Xopt-in=kotlin.Experimental"

            jvmTarget = "11"
        }
    }
}

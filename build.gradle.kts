// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    // Define versions in a single place
//    ext {
//        // Sdk and tools
//        compileSdkVersion = 31
//        minSdkVersion = 21
//        targetSdkVersion = 31
//        ktlint = "0.42.1"
//        detekt_version = "1.18.1"
//
//        // App dependencies
//        appCompatVersion = "1.4.1"
//        activityComposeVersion = "1.6.0-alpha03"
//        composeVersion = "1.2.0-beta03"
//        composeNavigationVersion = "2.5.0-rc01"
//        coreTestingVersion = "2.1.0"
//        espressoVersion = "3.4.0"
//        gradleVersion = "7.2.1"
//        junitVersion = "4.13.2"
//        kotlinVersion = "1.6.21"
//        ktxVersion = "1.7.0"
//        materialVersion = "1.7.0-alpha02"
//        material3Version = "1.0.0-alpha12"
//        runnerVersion = "1.0.1"
//        truthVersion = "1.0.1"
//        testExtJunit = "1.1.2"
//        uiAutomatorVersion = "2.2.0"
//    }

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradleVersion}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}")
    }
}

plugins {
    id("com.github.ben-manes.versions")
    id("io.gitlab.arturbosch.detekt")
    id("org.jlleitschuh.gradle.ktlint")
}

subprojects {
    repositories {
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

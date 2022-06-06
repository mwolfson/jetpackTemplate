plugins {
    id("com.android.application")
    id("kotlin-android")
    id("io.gitlab.arturbosch.detekt")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.material.demo"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = '1.0'
        testInstrumentationRunner = 'androidx.test.runner.AndroidJUnitRunner'

        vectorDrawables.useSupportLibrary = true
    }

    signingConfigs {
        // We use a bundled debug keystore, to allow debug builds from CI to be upgradable
        debug {
            storeFile = rootProject.file('debug.keystore')
            storePassword = 'android'
            keyAlias = 'androiddebugkey'
            keyPassword = 'android'
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.debug
        }
        release {
            minifyEnabled = true
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
        applicationVariants.all{
            variant ->
                variant.outputs.each{
                    output->
                        def name = "m3DemoApp-${variant.buildType.name}-${variant.versionName}-${getDate()}.apk"
                        output.outputFileName = name
                }
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = '11'
    }

    buildFeatures {
        compose = true

        // Disable unused AGP features
        buildConfig = true
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.composeVersion
    }
    packagingOptions {
        jniLibs {
            excludes += ['META-INF/licenses/**']
        }
        resources {
            excludes += ['META-INF/licenses/**', 'META-INF/AL2.0', 'META-INF/LGPL2.1']
        }
    }

    if (System.getProperty("idea.active") != "true") {
        lintOptions {
            quiet = false
            abortOnError = true
            lintConfig = file("${project.rootDir}/analysis/lint/lint.xml")
            htmlOutput = file("${project.buildDir}/reports/lint/lint.html")
        }
    }

}

static def getDate() {
    new Date().format('MMMddYYYY_HHmm')
}

dependencies {
    //Base libs and Jetpack
    with(Deps.AndroidX) {
        implementation(appcompat)
    }

    with(Deps.Kotlinx) {
        implementation(coreKtx)
    }

    implementation ("com.google.android.material:material:1.7.0-alpha02")

    // Compose
    with(Deps.Compose) {
        implementation(compiler)
        implementation(ui)
        implementation(runtime)
        implementation(activity)
        implementation(uiGraphics)
        implementation(uiTooling)
        implementation(foundationLayout)
        implementation(material)
        implementation(materialIconsExtended)
    }

    // Accompianist
    implementation ("com.google.accompanist:accompanist-navigation-material:0.23.1")
    implementation ("androidx.navigation:navigation-compose:2.5.0-rc01")

    // Util libs
    implementation ('io.coil-kt:coil-compose:2.1.0')

    with(Deps.Test) {
        testImplementation(junit)
        androidTestImplementation(androidXTestJUnit)
        testImplementation(testCore)

        // Compose testing dependencies
        androidTestImplementation(composeUiTest)
        androidTestImplementation(composeUiTestJUnit)
        debugImplementation(composeUiTestManifest)
    }

}
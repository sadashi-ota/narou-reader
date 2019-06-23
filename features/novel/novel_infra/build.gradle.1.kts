import de.mannodermaus.gradle.plugins.junit5.junitPlatform

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("de.mannodermaus.android-junit5")
    id("jacoco")
}

jacoco {
    toolVersion = "0.8.3"
}

android {
    compileSdkVersion(Deps.Versions.compileSdk)
    defaultConfig {
        applicationId = "com.sadashi.client.chatwork"
        minSdkVersion(Deps.Versions.minSdk)
        targetSdkVersion(Deps.Versions.compileSdk)
        versionCode = 1
        versionName = "0.0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    sourceSets {
        getByName("main").java.srcDirs("src/main/kotlin")
        getByName("test").java.srcDirs("src/test/kotlin")
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    testOptions {
        junitPlatform {
            filters {
                includeEngines("spek2")
            }
            jacocoOptions {
                html.enabled = true
                xml.enabled = true
                csv.enabled = false
                excludedClasses.addAll(
                    listOf(
                        "**/di/*.class",
                        "**/extensions/*.class",
                        "**/ui/**/*.class",
                        "**/utility/*.class"
                    )
                )
            }
        }
    }
}

dependencies {
    Deps.libraries.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }
}

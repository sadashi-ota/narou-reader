import com.android.build.gradle.internal.dsl.BuildType
import de.mannodermaus.gradle.plugins.junit5.junitPlatform

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("de.mannodermaus.android-junit5")
}

Prop.loadProperties("$rootDir/properties/secrets.properties")

android {
    compileSdkVersion(Deps.Versions.compileSdk)
    defaultConfig {
        minSdkVersion(Deps.Versions.minSdk)
        targetSdkVersion(Deps.Versions.compileSdk)
    }
    buildTypes {
        getByName("debug") {
            setCommonBuildConfig(this)
            isMinifyEnabled = false
        }
        getByName("release") {
            setCommonBuildConfig(this)
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

fun setCommonBuildConfig(buildType: BuildType) {
    buildType.buildConfigField("String", "API_DOMAIN", "\"${Prop.map["apiDomain"]}\"")
    buildType.buildConfigField("String", "HTML_DOMAIN", "\"${Prop.map["htmlDomain"]}\"")
}

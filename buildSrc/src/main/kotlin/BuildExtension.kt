import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BuildType
import de.mannodermaus.gradle.plugins.junit5.junitPlatform
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.plugin.use.PluginDependenciesSpec

fun PluginDependenciesSpec.basePlugin() {
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("de.mannodermaus.android-junit5")
    id("jacoco")
}

fun Project.baseProc() {
    Prop.loadProperties("$rootDir/properties/secrets.properties")
}

fun BaseExtension.baseConfiguration() {
    compileSdkVersion(Deps.Versions.compileSdk)
    buildToolsVersion = Deps.Versions.buildTools

    defaultConfig {
        minSdkVersion(Deps.Versions.minSdk)
        targetSdkVersion(Deps.Versions.compileSdk)
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

fun setCommonBuildConfig(buildType: BuildType) {
    buildType.buildConfigField("String", "API_DOMAIN", "\"${Prop.map["apiDomain"]}\"")
    buildType.buildConfigField("String", "HTML_DOMAIN", "\"${Prop.map["htmlDomain"]}\"")
}

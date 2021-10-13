import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.plugin.use.PluginDependenciesSpec

fun PluginDependenciesSpec.basePlugin() {
    id(Deps.Plugin.kotlin)
    id(Deps.Plugin.kapt)
    id(Deps.Plugin.jacoco)
    id(Deps.Plugin.androidJunit5)
}

fun LibraryExtension.baseConfiguration() {
    compileSdk = Deps.Versions.compileSdk

    defaultConfig {
        minSdk = Deps.Versions.minSdk
        targetSdk = Deps.Versions.compileSdk
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    sourceSets.forEach {
        it.java.srcDirs("src/$it.name/kotlin")
    }

    compileOptions {
        sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
        targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
    }
}

fun BaseAppModuleExtension.baseConfiguration() {
    compileSdk = Deps.Versions.compileSdk

    defaultConfig {
        minSdk = Deps.Versions.minSdk
        targetSdk = Deps.Versions.compileSdk
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    sourceSets.forEach {
        it.java.srcDirs("src/$it.name/kotlin")
    }

    compileOptions {
        sourceCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
        targetCompatibility = org.gradle.api.JavaVersion.VERSION_1_8
    }
}

@file:Suppress("SpellCheckingInspection")

plugins {
    id(Deps.Plugin.library)
    basePlugin()
}

android {
    baseConfiguration()
    kotlinOptions {
        jvmTarget = Deps.Versions.jvmTarget
        apiVersion = "1.4"
        languageVersion = "1.4"
        freeCompilerArgs = listOf("-Xinline-classes")
    }
}

jacoco {
    toolVersion = Deps.Versions.jacoco
}

dependencies {
    Deps.frameworkLibraries.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }
}

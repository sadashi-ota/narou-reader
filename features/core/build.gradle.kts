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
    }
}

jacoco {
    toolVersion = Deps.Versions.jacoco
}

dependencies {
    Deps.uiLibraries.forEach { implementation(it) }
    Deps.frameworkLibraries.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }
}

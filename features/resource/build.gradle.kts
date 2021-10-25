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

dependencies {
    Deps.uiLibraries.forEach { implementation(it) }
}

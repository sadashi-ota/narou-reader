plugins {
    id(Deps.Plugin.library)
    basePlugin()
}

android {
    baseConfiguration()
    kotlinOptions {
        jvmTarget = Deps.Versions.jvmTarget
    }
}

dependencies {
    Deps.uiLibraries.forEach { implementation(it) }
}

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
    implementation(project(":novel_domain"))
    implementation(Deps.Lib.Dagger.core)
    kapt(Deps.Lib.Dagger.compiler)

    Deps.frameworkLibraries.forEach { implementation(it) }
    Deps.infraLibraries.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }

}

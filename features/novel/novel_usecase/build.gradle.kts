plugins {
    id(Deps.Plugin.library)
    basePlugin()
}

jacoco {
    toolVersion = Deps.Versions.jacoco
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
    implementation(project(":novel_domain"))
    implementation(Deps.Lib.Dagger.core)
    kapt(Deps.Lib.Dagger.compiler)

    Deps.frameworkLibraries.forEach { implementation(it) }
    Deps.infraLibraries.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }

}

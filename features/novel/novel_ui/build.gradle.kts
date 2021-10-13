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

jacoco {
    toolVersion = Deps.Versions.jacoco
}

dependencies {
    implementation(project(":core"))
    implementation(project(":resource"))
    implementation(project(":novel_usecase"))
    implementation(project(":novel_domain"))

    implementation(Deps.Lib.Dagger.core)
    kapt(Deps.Lib.Dagger.compiler)

    implementation(Deps.Lib.AndroidX.Lifecycle.runtime)
    kapt(Deps.Lib.AndroidX.Lifecycle.compiler)

    Deps.frameworkLibraries.forEach { implementation(it) }
    Deps.uiLibraries.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }
}

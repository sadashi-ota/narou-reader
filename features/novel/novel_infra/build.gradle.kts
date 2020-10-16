plugins {
    id("com.android.library")
    basePlugin()
}

baseProc()
jacoco {
    toolVersion = "0.8.3"
}

android {
    baseConfiguration()
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":novel_domain"))
    implementation(Deps.Dagger.core)
    kapt(Deps.Dagger.compiler)

    Deps.frameworkLibraries.forEach { implementation(it) }
    Deps.infraLibraries.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }

}

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
}

dependencies {
    implementation(project(":core"))
    implementation(project(":resource"))
    implementation(project(":novel_domain"))
    implementation(project(":novel_infra"))

    implementation(Deps.Dagger.core)
    kapt(Deps.Dagger.compiler)

    Deps.frameworkLibraries.forEach { implementation(it) }
    Deps.uiLibraries.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }
}

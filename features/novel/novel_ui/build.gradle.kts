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
    implementation(project(":novel_domain"))
    Deps.libraries.forEach { implementation(it) }
    Deps.uiLibraries.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }
}

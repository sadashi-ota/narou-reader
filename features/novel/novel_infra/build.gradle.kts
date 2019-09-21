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
    Deps.frameworkLibraries.forEach { implementation(it) }
    Deps.infraLibraries.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }
}

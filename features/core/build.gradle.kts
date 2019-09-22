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
    Deps.uiLibraries.forEach { implementation(it) }
    Deps.frameworkLibraries.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }
}

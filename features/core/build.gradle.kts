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
    Deps.libraries.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }
}

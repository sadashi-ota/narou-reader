plugins {
    id("com.android.application")
    basePlugin()
}

baseProc()
jacoco {
    toolVersion = "0.8.3"
}

android {
    baseConfiguration()

    defaultConfig {
        applicationId = "com.sadashi.reader.novel.narou"
        versionCode = 1
        versionName = "0.0.1"
    }
}

dependencies {
    Deps.libraries.forEach { implementation(it) }
    Deps.uiLibrary.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }
}


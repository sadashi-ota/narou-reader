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
        applicationId = "com.sadashi.novel.reader.narou"
        versionCode = 1
        versionName = "0.0.1"
    }
}

dependencies {
    implementation(project(":resource"))
    Deps.frameworkLibraries.forEach { implementation(it) }
    Deps.uiLibraries.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }
}


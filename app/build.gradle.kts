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
        applicationId = "jp.sadashi.novel.reader.narou"
        versionCode = 1
        versionName = "0.0.1"
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":resource"))
    implementation(project(":novel_ui"))

    Deps.uiLibraries.forEach { implementation(it) }

    implementation(Deps.Dagger.core)
    kapt(Deps.Dagger.compiler)
}


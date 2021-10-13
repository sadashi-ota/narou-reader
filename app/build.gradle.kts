plugins {
    id(Deps.Plugin.application)
    basePlugin()
}

android {
    baseConfiguration()

    defaultConfig {
        applicationId = "jp.sadashi.narou.reader.novel"
        versionCode = 1
        versionName = "0.0.1"
    }

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
    implementation(project(":novel_di"))
    implementation(project(":novel_ui"))

    Deps.uiLibraries.forEach { implementation(it) }

    implementation(Deps.Lib.Dagger.core)
    kapt(Deps.Lib.Dagger.compiler)}
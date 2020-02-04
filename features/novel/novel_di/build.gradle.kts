import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    basePlugin()
}

baseProc()

android {
    baseConfiguration()
}

dependencies {
    implementation(project(":core"))
    implementation(project(":novel_ui"))
    implementation(project(":novel_usecase"))
    implementation(project(":novel_domain"))
    implementation(project(":novel_infra"))

    implementation(Deps.Dagger.core)
    kapt(Deps.Dagger.compiler)

    Deps.frameworkLibraries.forEach { implementation(it) }
    Deps.uiLibraries.forEach { implementation(it) }
}


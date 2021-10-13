import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Deps.Plugin.library)
    basePlugin()
}

android {
    baseConfiguration()
}

dependencies {
    implementation(project(":core"))
    implementation(project(":novel_ui"))
    implementation(project(":novel_usecase"))
    implementation(project(":novel_domain"))
    implementation(project(":novel_infra"))

    implementation(Deps.Lib.Dagger.core)
    kapt(Deps.Lib.Dagger.compiler)

    Deps.frameworkLibraries.forEach { implementation(it) }
    Deps.uiLibraries.forEach { implementation(it) }
}


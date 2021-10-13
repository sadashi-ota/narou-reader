import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Deps.Plugin.library)
    basePlugin()
}

android {
    baseConfiguration()
    kotlinOptions {
        jvmTarget = Deps.Versions.jvmTarget
    }
}

jacoco {
    toolVersion = Deps.Versions.jacoco
}

dependencies {
    Deps.frameworkLibraries.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
    }
}

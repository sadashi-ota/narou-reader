import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
    Deps.frameworkLibraries.forEach { implementation(it) }
    Deps.testLibraries.forEach { testImplementation(it) }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-XXLanguage:+InlineClasses")
    }
}

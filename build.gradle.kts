// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath(Deps.Gradle.build)
        classpath(Deps.Gradle.kotlin)
        classpath(Deps.Gradle.androidJunit5)
        classpath(Deps.Gradle.versionsPlugin)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", type = Delete::class) {
    delete(rootProject.buildDir)
}


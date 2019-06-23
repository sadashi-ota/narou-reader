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
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        
    }
}

tasks.create("clean", type = Delete::class) {
    delete(rootProject.buildDir)
}

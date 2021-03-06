@file:Suppress("SpellCheckingInspection")

object Deps {
    object Versions {
        const val compileSdk = 30
        const val buildTools = "30.0.2"
        const val minSdk = 24
        const val kotlin = "1.4.10"
        const val lifecycle = "2.2.0"
        const val spek = "2.0.13"
        const val retrofit = "2.9.0"
        const val moshi = "1.9.2"
        const val dagger = "2.29.1"
    }

    object Gradle {
        const val build = "com.android.tools.build:gradle:4.1.0"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val androidJunit5 = "de.mannodermaus.gradle.plugins:android-junit5:1.6.2.0"
        const val versionsPlugin = "com.github.ben-manes:gradle-versions-plugin:0.33.0"
    }

    object Kotlin {
        const val std = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val common = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlin}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
        const val test = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.2.0"
        const val constraint = "androidx.constraintlayout:constraintlayout:2.0.0"
        const val coreKtx = "androidx.core:core-ktx:1.3.1"
        const val activityKtx = "androidx.activity:activity-ktx:1.1.0"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.2.5"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
        const val viewPager2 = "androidx.viewpager2:viewpager2:1.0.0"
    }

    object Lifecycle {
        const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    }

    private const val material = "com.google.android.material:material:1.2.1"

    object Dagger {
        const val core = "com.google.dagger:dagger:${Versions.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    }

    object RxJava2 {
        const val java = "io.reactivex.rxjava2:rxjava:2.2.19"
        const val android = "io.reactivex.rxjava2:rxandroid:2.1.1"
        const val kotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val adapterRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    }

    object OkHttp {
        const val logging = "com.squareup.okhttp3:logging-interceptor:4.9.0"
    }

    object Moshi {
        const val core = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    }

    object Jsoup {
        const val core = "org.jsoup:jsoup:1.13.1"
        const val converterJsoup = "pl.droidsonroids.retrofit2:converter-jspoon:1.3.2"
    }

    private const val picasso = "com.squareup.picasso:picasso:2.8"
    private const val commonsLang3 = "org.apache.commons:commons-lang3:3.11"

    object Spek2 {
        const val dsl = "org.spekframework.spek2:spek-dsl-jvm:${Versions.spek}"
        const val runner = "org.spekframework.spek2:spek-runner-junit5:${Versions.spek}"
    }

    private const val mockK = "io.mockk:mockk:1.9.3"

    val frameworkLibraries = listOf(
        Kotlin.std,
        Kotlin.common,
        Kotlin.reflect,
        RxJava2.java,
        RxJava2.android,
        RxJava2.kotlin
    )

    val infraLibraries = listOf(
        Retrofit.core,
        Retrofit.converterMoshi,
        Retrofit.adapterRxJava,
        OkHttp.logging,
        Moshi.core,
        Moshi.kotlin,
        Jsoup.core,
        Jsoup.converterJsoup,
        commonsLang3
    )

    val uiLibraries = listOf(
        AndroidX.appcompat,
        AndroidX.constraint,
        AndroidX.coreKtx,
        AndroidX.activityKtx,
        AndroidX.fragmentKtx,
        AndroidX.recyclerview,
        AndroidX.viewPager2,
        material,
        picasso
    )

    val testLibraries = listOf(
        Kotlin.test,
        Spek2.dsl,
        Spek2.runner,
        mockK
    )
}
@file:Suppress("SpellCheckingInspection")

object Deps {
    object Versions {
        const val compileSdk = 30
        const val buildTools = "30.0.2"
        const val minSdk = 26
        const val kotlin = "1.5.31"
        const val coroutines = "1.5.2"
        const val lifecycle = "2.3.1"
        const val navigation = "2.3.5"
        const val daggerHilt = "2.38.1"
        const val spek = "2.0.17"
        const val retrofit = "2.9.0"
        const val moshi = "1.9.2"
        const val dagger = "2.29.1"
        const val jacoco = "0.8.7"
        const val jvmTarget = "1.8"
    }

    object Gradle {
        const val build = "com.android.tools.build:gradle:7.0.4"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val androidJunit5 = "de.mannodermaus.gradle.plugins:android-junit5:1.8.0.0"
        const val versionsPlugin = "com.github.ben-manes:gradle-versions-plugin:0.39.0"
        const val navigation =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
        const val daggerHilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.daggerHilt}"
    }

    object Plugin {
        const val application = "com.android.application"
        const val library = "com.android.library"
        const val kotlin = "kotlin-android"
        const val kapt = "kotlin-kapt"
        const val navigation = "androidx.navigation.safeargs.kotlin"
        const val versions = "com.github.ben-manes.versions"
        const val daggerHilt = "dagger.hilt.android.plugin"
        const val androidJunit5 = "de.mannodermaus.android-junit5"
        const val jacoco = "jacoco"
    }

    object Lib {
        object Kotlin {
            const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
            const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

            object Coroutines {
                const val core =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
                const val android =
                    "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
            }
        }

        object AndroidX {
            const val core = "androidx.core:core-ktx:1.6.0"
            const val appCompat = "androidx.appcompat:appcompat:1.3.1"
            const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.0"
            const val recyclerView = "androidx.recyclerview:recyclerview:1.2.0"
            const val activityKtx = "androidx.activity:activity-ktx:1.1.0"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:1.2.5"
            const val viewPager2 = "androidx.viewpager2:viewpager2:1.0.0"

            object Lifecycle {
                const val viewModel =
                    "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
                const val liveData =
                    "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
                const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
                const val savedState =
                    "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
                const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
                const val java8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
            }

            object Navigation {
                const val fragment =
                    "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
                const val ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
                const val hilt = "androidx.hilt:hilt-navigation-fragment:1.0.0"
            }
        }

        const val material = "com.google.android.material:material:1.4.0"
        const val coil = "io.coil-kt:coil:1.3.2"
        const val logcat = "com.squareup.logcat:logcat:0.1"

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

        const val picasso = "com.squareup.picasso:picasso:2.8"
        const val commonsLang3 = "org.apache.commons:commons-lang3:3.11"
    }

    object Test {
        const val kotlin = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"

        object Spek2 {
            const val dsl = "org.spekframework.spek2:spek-dsl-jvm:${Versions.spek}"
            const val runner = "org.spekframework.spek2:spek-runner-junit5:${Versions.spek}"
        }

        const val mockK = "io.mockk:mockk:1.9.3"
    }

    val frameworkLibraries = listOf(
        Lib.Kotlin.stdLib,
        Lib.Kotlin.reflect,
        Lib.RxJava2.java,
        Lib.RxJava2.android,
        Lib.RxJava2.kotlin
    )

    val infraLibraries = listOf(
        Lib.Retrofit.core,
        Lib.Retrofit.converterMoshi,
        Lib.Retrofit.adapterRxJava,
        Lib.OkHttp.logging,
        Lib.Moshi.core,
        Lib.Moshi.kotlin,
        Lib.Jsoup.core,
        Lib.Jsoup.converterJsoup,
        Lib.commonsLang3
    )

    val uiLibraries = listOf(
        Lib.AndroidX.appCompat,
        Lib.AndroidX.constraintLayout,
        Lib.AndroidX.core,
        Lib.AndroidX.activityKtx,
        Lib.AndroidX.fragmentKtx,
        Lib.AndroidX.recyclerView,
        Lib.AndroidX.viewPager2,
        Lib.material,
        Lib.picasso
    )

    val testLibraries = listOf(
        Test.kotlin,
        Test.Spek2.dsl,
        Test.Spek2.runner,
        Test.mockK
    )
}
object Deps {
    object Versions {
        const val compileSdk = 28
        const val buildTools = "28.0.3"
        const val minSdk = 21
        const val kotlin = "1.3.50"
        const val spek = "2.0.7"
        const val retrofit = "2.5.0"
        const val moshi = "1.8.0"
    }

    object Gradle {
        const val build = "com.android.tools.build:gradle:3.5.0"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val androidJunit5 = "de.mannodermaus.gradle.plugins:android-junit5:1.5.1.0"
    }

    object Kotlin {
        const val std = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
        const val test = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.0.2"
        const val constraint = "androidx.constraintlayout:constraintlayout:1.1.3"
        const val coreKtx = "androidx.core:core-ktx:1.0.2"
        const val recyclerview = "androidx.recyclerview:recyclerview:1.0.0"
    }

    private const val material = "com.google.android.material:material:1.0.0"

    object RxJava2 {
        const val java = "io.reactivex.rxjava2:rxjava:2.2.12"
        const val android = "io.reactivex.rxjava2:rxandroid:2.1.1"
        const val kotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
        const val adapterRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    }

    object OkHttp {
        const val logging = "com.squareup.okhttp3:logging-interceptor:3.14.2"
    }

    object Moshi {
        const val core = "com.squareup.moshi:moshi:${Versions.moshi}"
        const val kotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    }

    object Jsoup {
        const val core = "org.jsoup:jsoup:1.11.2"
        const val converterJsoup = "pl.droidsonroids.retrofit2:converter-jspoon:1.3.2"
    }

    private const val picasso = "com.squareup.picasso:picasso:2.71828"
    private const val commonsLang3 = "org.apache.commons:commons-lang3:3.9"

    object Spek2 {
        const val dsl = "org.spekframework.spek2:spek-dsl-jvm:${Versions.spek}"
        const val runner = "org.spekframework.spek2:spek-runner-junit5:${Versions.spek}"
    }

    private const val mockK = "io.mockk:mockk:1.9.3"

    val libraries = listOf(
        Kotlin.std,
        Kotlin.reflect,
        RxJava2.java,
        RxJava2.android,
        RxJava2.kotlin,
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

    val uiLibrary = listOf(
        AndroidX.appcompat,
        AndroidX.constraint,
        AndroidX.coreKtx,
        AndroidX.recyclerview,
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
plugins {
    id("com.android.library")
    basePlugin()
}

baseProc()

android {
    baseConfiguration()
}

dependencies {
    Deps.uiLibraries.forEach { implementation(it) }
}

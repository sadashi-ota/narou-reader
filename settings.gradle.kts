include(":app")

val featuresDir = File("features")

include(":core")
project(":core").projectDir = File(featuresDir, "core")


val novelModuleDir = File(featuresDir, "novel")

include(":novel_infra")
project(":novel_infra").projectDir = File(novelModuleDir, "novel_infra")

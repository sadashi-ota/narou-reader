include(":app")

val featuresDir = File("features")

include(":novel_infra")
val novelModuleDir = File(featuresDir, "novel")
project(":novel_infra").projectDir = File(novelModuleDir, "novel_infra")
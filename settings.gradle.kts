include(":app")

val featuresDir = File("features")

include(":core", ":resource")
project(":core").projectDir = File(featuresDir, "core")
project(":resource").projectDir = File(featuresDir, "resource")

val novelModuleDir = File(featuresDir, "novel")

//include(":novel_domain", ":novel_infra")
include(":novel_domain", ":novel_ui", ":novel_infra")
project(":novel_domain").projectDir = File(novelModuleDir, "novel_domain")
project(":novel_ui").projectDir = File(novelModuleDir, "novel_ui")
project(":novel_infra").projectDir = File(novelModuleDir, "novel_infra")

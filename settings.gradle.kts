plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.7.0"
}

rootProject.name = "testing-assignments"

include("app")
include("math")
include("collection")
include("story")
include("selenium")

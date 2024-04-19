plugins {
    id("buildlogic.kotlin-library-conventions")
}

dependencies {
    implementation("com.github.doyaaaaaken:kotlin-csv-jvm:1.9.3")
    testImplementation("io.kotest:kotest-assertions-core")
    testImplementation("io.kotest:kotest-property")
}

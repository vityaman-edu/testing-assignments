plugins {
    id("buildlogic.kotlin-application-conventions")
}

dependencies {
    implementation("org.apache.commons:commons-text")
    implementation(project(":collection"))
}

application {
    mainClass = "org.example.app.AppKt"
}

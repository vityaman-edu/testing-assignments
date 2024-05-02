plugins {
    id("buildlogic.kotlin-library-conventions")
}

dependencies {
    testImplementation("org.seleniumhq.selenium:selenium-java:4.20.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.2")
    implementation("io.github.bonigarcia:webdrivermanager:4.2.0")
}

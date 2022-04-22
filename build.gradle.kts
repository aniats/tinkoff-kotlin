import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("com.adarshr.test-logger") version "3.2.0"
    application
}

group = "me.aniatselikova"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("junit:junit:4.13.2")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.0")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:5.2.2")
    testImplementation("io.mockk:mockk:1.12.3")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    this.testLogging {
        this.showStandardStreams = true
    }
}

application {
    mainClassName = "Main"
}
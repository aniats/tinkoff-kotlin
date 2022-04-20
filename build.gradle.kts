import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	id("com.adarshr.test-logger") version "3.2.0"
}

group = "ru.tinkoff.homework.lesson6"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
//	implementation("org.springdoc:springdoc-openapi-data-rest:1.6.0")
//	implementation("org.springdoc:springdoc-openapi-ui:1.6.0")
//	implementation("org.springdoc:springdoc-openapi-kotlin:1.6.0")

	implementation("io.springfox:springfox-boot-starter:3.0.0")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("junit:junit:4.13.1")
	implementation("junit:junit:4.13.1")

	implementation("com.google.code.gson:gson:2.9.0")
	testImplementation("com.ninja-squad:springmockk:3.1.1")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.0")
	testImplementation("io.kotest:kotest-runner-junit5-jvm:5.1.0")
	testImplementation("io.mockk:mockk:1.12.3")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	this.testLogging {
		this.showStandardStreams = true
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

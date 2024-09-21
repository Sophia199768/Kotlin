val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "2.0.20"
    kotlin("plugin.serialization") version "2.0.20"
    id("io.ktor.plugin") version "3.0.0-rc-1"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("com.example.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-client-core:3.0.0-rc-1")
    implementation("io.ktor:ktor-client-cio:3.0.0-rc-1")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.0-rc-1")
    implementation("io.ktor:ktor-client-content-negotiation:3.0.0-rc-1")
    implementation ("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")
    implementation ("com.jsoizo:kotlin-csv-jvm:1.10.0")
    testImplementation("io.ktor:ktor-server-test-host-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

plugins {
    `java-library`
    `maven-publish`
    kotlin("jvm")
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
    mavenCentral()
}

dependencies {
    api(libs.io.cucumber.cucumber.java)
    api(libs.com.fasterxml.jackson.core.jackson.databind)
    testImplementation(libs.net.datafaker.datafaker)
    testImplementation(libs.io.rest.assured.rest.assured)
    testImplementation(libs.io.rest.assured.json.path)
    testImplementation(libs.org.assertj.assertj.core)
    testImplementation(libs.io.cucumber.cucumber.junit)
    testImplementation(libs.org.junit.jupiter.junit.jupiter.api)
    testImplementation(libs.org.junit.jupiter.junit.jupiter.params)
    implementation(kotlin("stdlib-jdk8"))
    testImplementation(kotlin("test"))
}

group = "buddyrental"
version = "1.0-SNAPSHOT"
description = "buddyrental"

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
kotlin {
    jvmToolchain(23)
}
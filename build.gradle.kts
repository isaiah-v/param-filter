plugins {
    application
    kotlin("jvm") version "1.6.10"
}

group = "org.ivcode"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("org.ivcode.filter.MainKt")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("software.amazon.awssdk:ssm:2.17.194")
    implementation("org.slf4j:slf4j-nop:1.7.30")
    implementation("com.beust:jcommander:1.82")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.4.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
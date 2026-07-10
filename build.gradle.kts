plugins {
    java
    kotlin("jvm") version "2.2.0"
    id("io.qameta.allure") version "2.12.0"
}

group = "org.sdet.testing.app"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val cucumberVersion = "7.34.0"
val allureVersion = "2.33.0"
val extentVersion = "5.1.2"
val extentCucumberAdapterVersion = "1.14.0"
val slf4jVersion = "2.0.18"

val seleniumVersion = "4.33.0"
val junitVersion = "5.13.4"
val selenideVersion = "7.9.3"

val testcontainersVersion = "1.20.6"
val flywayVersion = "12.10.0"
val postgresqlVersion = "42.7.6"
val mysqlVersion = "9.2.0"

dependencies {

    // Selenium
    implementation("org.seleniumhq.selenium:selenium-java:$seleniumVersion")

    // Kotlin
    implementation(kotlin("stdlib"))

    // PostgreSQL
    implementation("org.postgresql:postgresql:$postgresqlVersion")

    // MySQL
    implementation("com.mysql:mysql-connector-j:$mysqlVersion")

    // Flyway
    implementation("org.flywaydb:flyway-core:$flywayVersion")
    implementation("org.flywaydb:flyway-database-postgresql:$flywayVersion")

    // JUnit 5
    testImplementation(platform("org.junit:junit-bom:$junitVersion"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    // Cucumber
    testImplementation(platform("io.cucumber:cucumber-bom:$cucumberVersion"))
    testImplementation("io.cucumber:cucumber-java")
    testImplementation("io.cucumber:cucumber-junit-platform-engine")
    testImplementation("io.cucumber:cucumber-picocontainer")

    // JUnit Platform Suite
    testImplementation("org.junit.platform:junit-platform-suite")

    // Allure
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-cucumber7-jvm")
    testImplementation("io.qameta.allure:allure-junit5")
 

    // Extent Reports
    testImplementation("com.aventstack:extentreports:$extentVersion")
    testImplementation(
        "tech.grasshopper:extentreports-cucumber7-adapter:$extentCucumberAdapterVersion"
    )

    // Logging
    testImplementation("org.slf4j:slf4j-simple:$slf4jVersion")

    // Selenide
    testImplementation("com.codeborne:selenide:$selenideVersion")

    // Testcontainers
    testImplementation("org.testcontainers:testcontainers:$testcontainersVersion")
    testImplementation("org.testcontainers:junit-jupiter:$testcontainersVersion")
    testImplementation("org.testcontainers:postgresql:$testcontainersVersion")

    testImplementation("io.qameta.allure:allure-cucumber7-jvm")
    testImplementation("io.qameta.allure:allure-junit5")
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("allure.results.directory", "$buildDir/allure-results")

    testLogging {
        events("passed", "skipped", "failed")
        showStandardStreams = true
    }

    if (System.getenv("CI") != null) {
        systemProperty("headless", "true")
        // Optionally narrow tests on CI by uncommenting the filter block below
        // filter { includeTestsMatching("postgreSqlCon.OrderTest") }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(22))
    }
}

kotlin {
    jvmToolchain(22)
}

tasks.test{
    name = "Run Allure Test"
    include("**/OrderTest.class")
    maxParallelForks = 1
}
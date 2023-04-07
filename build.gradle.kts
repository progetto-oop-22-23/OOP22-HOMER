plugins {
    // Apply the java plugin to add support for Java
    java

    // Apply the application plugin to add support for building a CLI application
    // You can run your app via task "run": ./gradlew run
    application

    /*
     * Adds tasks to export a runnable jar.
     * In order to create it, launch the "shadowJar" task.
     * The runnable jar will be found in build/libs/projectname-all.jar
     */
    id("com.github.johnrengelman.shadow") version "7.0.0"
    id("org.danilopianini.gradle-java-qa") version "1.6.0"
}

group = "HOMER"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val javaFXModules = listOf(
    "base",
    "controls",
    "fxml",
    "graphics"
)

val supportedPlatforms = listOf("linux", "mac", "win") // All required for OOP

dependencies {
    // Suppressions for SpotBugs
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.7.3")

    // JavaFX: comment out if you do not need them
    val javaFxVersion = 15
    for (platform in supportedPlatforms) {
        for (module in javaFXModules) {
            implementation("org.openjfx:javafx-$module:$javaFxVersion:$platform")
        }
    }

    // ControlsFX
    implementation("org.controlsfx:controlsfx:11.1.2")

    val jUnitVersion = "5.9.1"
    // JUnit API and testing engine
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

application {
    // Define the main class for the application
    mainClass.set("homer.App")
}
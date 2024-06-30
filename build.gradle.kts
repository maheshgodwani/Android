plugins {
    kotlin("jvm") version "1.8.10"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.openjfx:javafx-controls:17.0.2")
    implementation("org.openjfx:javafx-fxml:17.0.2")
}

application {
    mainClass.set("org.example.HelloWorldAppKt")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17)) // Ensure you're using the correct Java version
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.named<JavaExec>("run") {
    args = listOf(
        "--module-path", "C:\\Users\\mhsgo\\Downloads\\openjfx-23-ea+23_windows-x64_bin-sdk\\javafx-sdk-23\\lib",
        "--add-modules", "javafx.controls,javafx.fxml"
    )
}

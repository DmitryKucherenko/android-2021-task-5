// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    val kotlin_version = "1.5.10"

    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:4.2.2")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}


plugins {
    id("io.gitlab.arturbosch.detekt") version "1.18.1"
    id("org.jlleitschuh.gradle.ktlint") version "9.2.1"
}

subprojects {
    apply (plugin = "org.jlleitschuh.gradle.ktlint")
    configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
        debug.set(false)
    }

}

detekt {
    toolVersion = "1.18.0"
    config = files("config/detekt/detekt.yml")
    autoCorrect = true

    source = files("app/src/main/java", "app/src/main/kotlin")

    reports {
        html {
            enabled = true
            destination = file("app/build/detekt/detekt.html")
        }
    }
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
    // Target version of the generated JVM bytecode. It is used for type resolution.
    jvmTarget = "1.8"
}

allprojects {
    repositories {
        google()
        mavenCentral()

    }
}



tasks.register(name = "type",type = Delete::class){
    delete(rootProject.buildDir)
}

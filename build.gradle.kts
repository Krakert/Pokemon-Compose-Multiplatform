plugins {
    id("com.android.application").version(Versions.gradle).apply(false)
    id("com.android.library").version(Versions.gradle).apply(false)
    kotlin("multiplatform").version(Versions.kotlin).apply(false)
    id("org.jlleitschuh.gradle.ktlint").version(Versions.ktlint).apply(false)
    id("org.jetbrains.compose").version(Versions.composeMultiplatform).apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
}